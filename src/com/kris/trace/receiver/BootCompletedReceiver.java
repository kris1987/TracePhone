package com.kris.trace.receiver;

import com.kris.trace.manager.TraceManager;
import com.kris.trace.service.TraceService;
import com.kris.trace.utils.L;
import com.kris.trace.utils.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 *�����ֻ���������ÿ��������ȥ�Ƚϡ��ֻ����롡
 * @author <a href="mailto:kris1987@qq.com">Kris.lee</a>
 * @version 1.0
 */
public class BootCompletedReceiver extends BroadcastReceiver {

	public static final String BOOT_COMPLETED_ACTION = "android.intent.action.BOOT_COMPLETED";
	@Override
	public void onReceive(Context context, Intent intent) {
		L.i("���������ֻ���������");
		String action = intent.getAction();
		 if(action.equals(BOOT_COMPLETED_ACTION))
		 {
			 TraceManager manager = new TraceManager(context);
			 String password = manager.getPassword();
			 if(!Utils.isEmpty(password))
			 {
				 //�����Ϊ�գ���֤���Ѿ���ʼ�����ˣ���ȥ��������,�Ƚ� 
				 Intent service = new Intent(context,TraceService.class);
				 context.startService(service);
			 }
		 };   
	}

}
