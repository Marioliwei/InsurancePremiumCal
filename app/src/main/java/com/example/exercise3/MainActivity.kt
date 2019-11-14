package com.example.exercise3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?,
                                            view:View?,
                                            position: Int,
                                            id: Long){
        /*val selectedItem = spinnerAge.selectedItemPosition*/
        val selectedItem= spinnerAge.getItemAtPosition(position)
        Toast.makeText( this,"Selected Item = "+ selectedItem, Toast.LENGTH_LONG).show()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Handling item selected listebner for the spinner
        spinnerAge.onItemSelectedListener=this
        buttonCalculate.setOnClickListener{
            calculatePremium()
        }

    }
    private fun calculatePremium(){
        //get the age group
        val age: Int=spinnerAge.selectedItemPosition
        var extraMale=0
        var extraSmoker=0

         var premium = when(age){
            0->60 //Less than 17
             1-> 70 //17 to 25
             2->90
             3->120
             else->150
        }

        // get the gender selection. ID of radio button
        val gender = radioGroupGender.checkedRadioButtonId
            if(gender==R.id.radioButtonMale){
                extraMale =when(age){
                    0->0
                    1->50
                    2->100
                    3->150
                    else->200
                }

            }


        //Determine smoker or not
        val smoker: Boolean = checkBoxSmoker.isChecked
        if(smoker){
            //calculate premium for smoker
            extraSmoker = when(age){
                0->0
                1->100
                2->150
                3->200
                4->250
                else->300
            }
        }
        var totalPremium=(extraSmoker+ premium + extraMale )
        val symbol= Currency.getInstance(Locale.getDefault()).symbol
       // textViewPremium.text="RM${totalPremium}"
        textViewPremium.text=String.format("%s %d",symbol,totalPremium)
    }

    fun Reset(view: View) {
        checkBoxSmoker.setChecked(false)
        textViewPremium.text=null
        spinnerAge.setSelection(0)
        radioButtonMale.setChecked(false)
        radioButtonFemale.setChecked(false)

    }
}
