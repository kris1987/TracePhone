package com.kris.trace;

import com.kris.trace.manager.TraceManager;
import com.kris.trace.utils.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * ���뱣��������
 * @author <a href="mailto:kris1987@qq.com">Kris.lee</a>
 * @since 1.0.0 ����05:23:49
 * @version 1.0.0
 */
public class PasswordActivity extends Activity implements OnClickListener{
	private EditText  	etphone1;
	private EditText  	etphone2;
	private Button 		btnNext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password);
		etphone1 = (EditText)findViewById(R.id.et_passowrd1);
		etphone2 = (EditText)findViewById(R.id.et_passowrd2);
		btnNext  =(Button)findViewById(R.id.btn_next);
		btnNext.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_next:
			String p1 = etphone1.getText().toString();
			if(Utils.isEmpty(p1))
			{
				Toast.makeText(this, "����������", Toast.LENGTH_SHORT).show();
				return;
			}
			String p2 = etphone2.getText().toString();
			if(Utils.isEmpty(p2))
			{
				Toast.makeText(this, "������ȷ������", Toast.LENGTH_SHORT).show();
				return;
			}
			if(!p1.equals(p2))
			{
				Toast.makeText(this, "������������벻ƥ��", Toast.LENGTH_SHORT).show();
				return;
			}
			TraceManager manager = new TraceManager(this);
			manager.setPassword(p1);
			manager.setStep(TraceManager.SETP_INFO);
			Intent intent = new Intent(this,InfoActivity.class);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}
	
}
