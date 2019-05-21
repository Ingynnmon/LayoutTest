package com.app.layouttest

import android.app.AlertDialog
import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var progressDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //depreciated in API 26 ProgressDialog
       /* val progressDialog=ProgressDialog(this)
        progressDialog.setMessage("Downloading..")
        progressDialog.setCancelable(false)
        progressDialog.show()

         Handler().postDelayed({progressDialog.dismiss()},5000)*/


        //Custom Progress Bar with layout
       /* val builder=AlertDialog.Builder(this)
        val dialogView=layoutInflater.inflate(R.layout.progress_dialog,null)
        val message=dialogView.findViewById<TextView>(R.id.progressText)
        message.text="Downloading.."
        builder.setView(dialogView)
        builder.setCancelable(false)
        val dialog=builder.create()
        dialog.show()

        Handler().postDelayed({dialog.dismiss()},5000)
*/


        //Prograss Bar testing
        val b1 =findViewById<View>(R.id.button) as Button
        val b2 =findViewById<View>(R.id.button2) as Button

        b1.setOnClickListener {
            progressDialog = ProgressDialog(this@MainActivity)
            progressDialog!!.setMessage("Loading...") // Setting Message
            progressDialog!!.setTitle("ProgressDialog") // Setting Title
            progressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER) // Progress Dialog Style Spinner
            progressDialog!!.show() // Display Progress Dialog
            progressDialog!!.setCancelable(false)
            Thread(Runnable {
                try {
                    Thread.sleep(3000)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                progressDialog!!.dismiss()
            }).start()
        }

        b2.setOnClickListener(object : View.OnClickListener {
             var handle: Handler = object : Handler() {
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    progressDialog!!.incrementProgressBy(2) // Incremented By Value 2
                }
            }

            override fun onClick(v: View) {
                progressDialog = ProgressDialog(this@MainActivity)
                progressDialog!!.max = 100 // Progress Dialog Max Value
                progressDialog!!.setMessage("Loading...") // Setting Message
                progressDialog!!.setTitle("ProgressDialog") // Setting Title
                progressDialog!!.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL) // Progress Dialog Style Horizontal
                progressDialog!!.show() // Display Progress Dialog
                progressDialog!!.setCancelable(false)
                Thread(Runnable {
                    try {
                        while (progressDialog!!.progress <= progressDialog!!.max) {
                            Thread.sleep(200)
                            handle.sendMessage(handle.obtainMessage())
                            if (progressDialog!!.progress == progressDialog!!.max) {
                                progressDialog!!.dismiss()
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }).start()
            }
        })
    }
}
