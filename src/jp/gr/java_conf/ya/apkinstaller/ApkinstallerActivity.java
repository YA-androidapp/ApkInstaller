package jp.gr.java_conf.ya.apkinstaller; // Copyright (c) 2015 YA <ya.androidapp@gmail.com> All rights reserved.

import java.io.File;

import jp.gr.java_conf.ya.apkinstaller.SelectFileDialog.onSelectFileDialogListener;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class ApkinstallerActivity extends Activity implements onSelectFileDialogListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		SelectFile();
	}

	protected SelectFileDialog _dlgSelectFile;

	private void SelectFile() {
		_dlgSelectFile = new SelectFileDialog(this);
		_dlgSelectFile.Show(getString(R.string.path));
	}

	public void onFileSelected_by_SelectFileDialog(final File file) {
		if (file != null) {
			try {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
				startActivity(intent);
			} catch (Exception e) {
			}
		}
		_dlgSelectFile = null;
		finish();
	}

	@Override
	public void onPause() {
		if (_dlgSelectFile != null) {
			_dlgSelectFile.onPause();
		}
		super.onPause();
	}
}
