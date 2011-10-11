package com.kris.trace.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;

import com.kris.trace.manager.TraceManager;
import com.kris.trace.utils.L;
/**
 * ������Ҫ�����ڱȽϵ�ǰ�ĵ绰���趨���Ƿ���һ���ģ������һ�����򷢶���
 * @author <a href="mailto:kris1987@qq.com">Kris.lee</a>
 * @since 1.0.0 ����10:27:30
 * @version 1.0.0
 */
public class TraceService extends Service {
	private TraceManager manager ;
	private Context context;
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		context = this;
		manager = new TraceManager(context);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				L.i("��ʼ�˶Աȵķ��񡣡���");
				String origSim = manager.getSim();
				String nowSim = manager.getSimSerialNumber();
				String ownPhone = manager.getPhoneNumber();
				String prePhone = manager.getPreparePhoneNumber();
				L.i("ownPhone:"+ownPhone+"#prePhone:"+prePhone);
				if(!origSim.equals(nowSim))
				{
					//if not the same ,so send a SMS
					L.e("sim��������");
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(prePhone, null, "����ֻ�("+ownPhone+")���ڱ���ǰ���û�ʹ�ã����ʵ����Ϣ��", null, null);
				}
				TraceService.this.stopSelf();
			}
		}).start();
		
	}

}
