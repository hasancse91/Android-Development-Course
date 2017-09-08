/**
 * This interface will work to update TextView text of MainActivity
 * when response come from server.
 * uiUpdate() method will be called from NetworkCall class inside onResponse() and onFailure() method
 * of sendData() method
 */

package com.hellohasan.forthclass.UiClass;

import com.hellohasan.forthclass.ModelClass.ResponseModel;

public interface CallBackInterface {
    void uiUpdate(ResponseModel responseModel);
}
