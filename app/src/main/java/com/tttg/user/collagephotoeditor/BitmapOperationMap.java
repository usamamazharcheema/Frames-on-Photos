package com.tttg.user.collagephotoeditor;

import android.graphics.Matrix;

public class BitmapOperationMap {
	DraggableBitmap mBitmap;
	Matrix mOperationMtx;
	OPERATION mOpt;

	public enum OPERATION {
		NEW, ADD, DELETE
	}


	public BitmapOperationMap(DraggableBitmap bmp, Matrix mtx, OPERATION op) {
		mBitmap = bmp;
		mOperationMtx = mtx;
		mOpt = op;
	}

	public DraggableBitmap getDraggableBitmap() {
		return mBitmap;
	}

	public Matrix getOperationMatrix() {
		return mOperationMtx;
	}

	public OPERATION getOption() {
		return mOpt;
	}
}
