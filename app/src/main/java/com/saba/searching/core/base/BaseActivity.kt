package com.saba.searching.core.base


import androidx.appcompat.app.AppCompatActivity
import com.saba.searching.view.waitingDialog.WaitingDialog

abstract class BaseActivity : AppCompatActivity() {


    val waitingDialog: WaitingDialog = WaitingDialog()
    var isShow = false

    fun showWaitingDialog() {
        try {
            dismissWaitingDialog()
        } catch (e: Exception) {
        }
        if (!waitingDialog.isAdded && !isShow) {
            isShow = true
            waitingDialog.isCancelable = true
            waitingDialog.show(supportFragmentManager, "")
        }
    }

    fun showHardWaitingDialog() {
        try {
            dismissWaitingDialog()
        } catch (e: Exception) {
        }
        if (!waitingDialog.isAdded && !isShow) {
            isShow = true
            waitingDialog.isCancelable = false
            waitingDialog.show(supportFragmentManager, "")
        }
    }

    fun dismissWaitingDialog() {
        if (waitingDialog.isAdded && isShow) {
            waitingDialog.dismiss()
        }
        isShow = false
    }


}
