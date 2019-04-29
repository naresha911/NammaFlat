package com.savera.nammaflat.Requests.Sheets;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.savera.nammaflat.AuthActivity;
import com.savera.nammaflat.modal.FBModalEntityList;
import com.savera.nammaflat.modal.ServiceRequestModal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AsyncLoadSheets extends SheetsAsyncTask {

    public AsyncLoadSheets(AuthActivity authActivity, String spreadSheetId, String range) {
        super(authActivity, spreadSheetId, range);
    }

    @Override
    protected void Run() throws IOException {
        Sheets.Spreadsheets.Values.Get request =
                this.mService.spreadsheets().values().get(mSpreadSheetId, mRange);

        ValueRange response = request.execute();
        List<List<Object>> values = response.getValues();
        if (values != null) {
            mServiceRequests = new FBModalEntityList();
            for (List row : values) {
                if(!row.isEmpty()) {
                    List<String> colStrs = new ArrayList<>();
                    if(!colStrs.isEmpty())
                    {
                        for (Object col : row) {
                            colStrs.add(col.toString());

                            ServiceRequestModal dataRequest = new ServiceRequestModal();
                            dataRequest.FillData(colStrs);

                            mServiceRequests.Add(dataRequest);
                        }
                    }
                }
            }
        }

    }
}
