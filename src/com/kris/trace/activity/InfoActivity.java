package com.kris.trace.activity;

import com.kris.trace.R;
import com.kris.trace.R.id;
import com.kris.trace.R.layout;
import com.kris.trace.R.string;
import com.kris.trace.manager.TraceManager;
import com.kris.trace.utils.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * ��ȡ�����ֻ�����Ϣ
 * @author <a href="mailto:kris1987@qq.com">Kris.lee</a>
 * @since 1.0.0 ����05:23:49
 * @version 1.0.0
 */
public class InfoActivity extends Activity implements OnClickListener{
	private EditText  	etOwnPhone;
	private EditText  	etPrePhone;
	private Button 		btnComp;
	
	private TextView	tvSim;
	private TextView	tvPhone;
	
	private TraceManager	traceManager;
	private String sim;
	private String localPhone;
	private boolean isInputOwnPhone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		traceManager = new TraceManager(this);
		
		//ʵ������� 
		etOwnPhone = (EditText)findViewById(R.id.et_phone);
		etPrePhone = (EditText)findViewById(R.id.edittext);
		btnComp = (Button)findViewById(R.id.btn_comp);
		btnComp.setOnClickListener(this);
		
		tvSim = (TextView)findViewById(R.id.tv_sim);
		tvPhone = (TextView)findViewById(R.id.tv_phone);
		
		//init the data 
		String notRead = getString(R.string.can_not_read);
		sim = traceManager.getSimSerialNumber();
		if(Utils.isEmpty(sim))
		{
			tvSim.setText(notRead);
			btnComp.setEnabled(false);
		}
		else
		{
			tvSim.setText(sim);
		}
		localPhone = traceManager.getLocalPhoneNumber();
		if(Utils.isEmpty(localPhone))
		{		
			tvPhone.setVisibility(View.GONE);
			etOwnPhone.setVisibility(View.VISIBLE);
			isInputOwnPhone = true;
		}
		else
		{
			tvPhone.setVisibility(View.VISIBLE);
			tvPhone.setText(localPhone);
			etOwnPhone.setVisibility(View.GONE);
			isInputOwnPhone = false;
		}
	}
	

	private Handler mHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
		
	};
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_comp:
			if(isInputOwnPhone)
			{
				localPhone = etOwnPhone.getText().toString();
				if(Utils.isEmpty(localPhone.trim()))
				{
					Toast.makeText(this, "���������ѵĺ���", Toast.LENGTH_SHORT).show();
					return;
				}
				if(!Utils.matchPhone(localPhone))
				{
					Toast.makeText(this, "�������Լ��ĵ绰���벻�԰�", Toast.LENGTH_SHORT).show();
					return;
				}
			}
			
			String prePhone = etPrePhone.getText().toString();
			if(Utils.isEmpty(prePhone.trim()))
			{
				Toast.makeText(this, "������Ԥ������", Toast.LENGTH_SHORT).show();
				return;
			}
			if(!Utils.matchPhone(prePhone))
			{
				Toast.makeText(this, "������һ�����õ�Ԥ���绰����", Toast.LENGTH_SHORT).show();
				return;
			}
			traceManager.setSim(sim);
			traceManager.setPhoneNumber(localPhone);
			traceManager.setStep(TraceManager.SETP_OVER);
			traceManager.setPreparePhoneNumber(prePhone);
			Intent intent = new Intent(this,TracingPhoneActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}
}
