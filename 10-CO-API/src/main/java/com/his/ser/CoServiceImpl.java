package com.his.ser;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.his.dto.ApplicationReg;
import com.his.dto.EDEntityDto;
import com.his.entity.COEntity;
import com.his.feigns.EDFeign;
import com.his.feigns.FeignAR;
import com.his.repo.CoRepo;
import com.his.utils.EmailUtils;
import com.his.utils.NoticeGeneration;
import com.his.utils.S3Utils;

@Service
public class CoServiceImpl implements CoService {

	private CoRepo coRepo;

	private EDFeign edFeign;

	private FeignAR arFeign;

	private EmailUtils emailUtils;

	private S3Utils s3Utils;

	private NoticeGeneration notice;

	public CoServiceImpl(CoRepo coRepo, EDFeign edFeign, FeignAR arFeign, EmailUtils emailUtils, S3Utils s3Utils,
			NoticeGeneration notice) {
		this.coRepo = coRepo;
		this.edFeign = edFeign;
		this.arFeign = arFeign;
		this.emailUtils = emailUtils;
		this.s3Utils = s3Utils;
		this.notice = notice;
	}

	@Override
	public List<COEntity> getNotices(Integer appNumber, String status) {
		return coRepo.fetchNotices(appNumber, status);
	}

	@Override
	public boolean printNotice(Integer coNoticeId) throws Exception {

		File noticePdf = new File(coNoticeId + ".pdf");
		COEntity entity = coRepo.findById(coNoticeId).orElseThrow();
		Integer appNumber = entity.getAppNumber();

		ApplicationReg appData = getAppDataUsingFeignClient(appNumber);
		EDEntityDto edData = getEligDataUsingFeignClient(appNumber);

		String eligStatus = edData.getEligStatus();

		if (eligStatus.equals("Approved")) {
			notice.generateApprovedNotice(edData, noticePdf, appData);
		} else {
			notice.generateDeniedNotice(edData, noticePdf, appData);
		}
		String s3Url = "";
		// uploadNoticeToS3(noticePdf);

		entity.setCoStatus("History");
		entity.setCS3Url(s3Url);
		entity.setCoPrintDate(LocalDate.now());

		coRepo.save(entity);
		String sub = "Eligibility Notice";
		String body = "Please find your notice as an attachment";

		return emailUtils.sendEmail(sub, body, appData.getGmail(), noticePdf);
	}

	public String uploadNoticeToS3(File file) {
		return s3Utils.saveFileInS3(file);
	}

	@Override
	public void sendRemainderNotice() {
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		try {
			List<EDEntityDto> list = edFeign.getExpiringApplictaions(LocalDate.now().toString());
			for (EDEntityDto e : list) {
				threadPool.submit(new Callable<Void>() {
					@Override
					public Void call() throws Exception {
						notice.generateExpringNotice(e);
						return null;
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}
	}

	private EDEntityDto getEligDataUsingFeignClient(Integer appNumber) {
		return edFeign.checkEligibility(appNumber);
	}

	private ApplicationReg getAppDataUsingFeignClient(Integer appNumber) {
		return arFeign.getApplictionByAppNO(appNumber);
	}
}
