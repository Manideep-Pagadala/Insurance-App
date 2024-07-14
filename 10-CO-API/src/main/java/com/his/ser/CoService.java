package com.his.ser;

import java.util.List;

import com.his.entity.COEntity;

public interface CoService {

	public List<COEntity> getNotices(Integer appNumber, String status);

	public boolean printNotice(Integer coNoticeId) throws Exception;

	public void sendRemainderNotice();

}
