package com.busycount.common.sample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author : BusyCount
 * Date : 2021/03/02
 * Describe :
 **/
class MyVm : ViewModel() {

    val status = MutableLiveData<Boolean>()
}