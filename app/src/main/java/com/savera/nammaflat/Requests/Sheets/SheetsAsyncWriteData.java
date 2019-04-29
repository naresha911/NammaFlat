package com.savera.nammaflat.Requests.Sheets;

import com.savera.nammaflat.AuthActivity;

import java.io.IOException;

public class SheetsAsyncWriteData extends SheetsAsyncTask {

    public SheetsAsyncWriteData(AuthActivity authActivity, String spreadSheetId, String range) {
        super(authActivity, spreadSheetId, range);
    }

    @Override
    protected void Run() throws IOException {

    }
}
