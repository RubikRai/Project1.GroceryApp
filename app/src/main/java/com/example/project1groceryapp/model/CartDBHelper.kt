package com.example.project1groceryapp.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.project1groceryapp.data.Product

class CartDBHelper(private val context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 2) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CART_TABLE =
            ("CREATE TABLE " + TABLE_NAME + " ( " + KEY_ID + " VARCHAR PRIMARY KEY, "
                    + KEY_PRODUCT_NAME+ " VARCHAR, " + KEY_QUANTITY + " INTEGER, " + KEY_PRICE + " INTEGER )")
        db?.execSQL(CREATE_CART_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = ("DROP TABLE IF EXISTS $TABLE_NAME")
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun clearCart(){
        val db = this.writableDatabase
        val DROP_TABLE = ("DROP TABLE IF EXISTS $TABLE_NAME")
        db.execSQL(DROP_TABLE)
    }

    companion object{
        const val DATABASE_NAME = "CartDB"
        const val TABLE_NAME = "PRODUCT"
        const val KEY_ID = "id"
        const val KEY_PRODUCT_NAME = "Name"
        const val KEY_QUANTITY =  "Quantity"
        const val KEY_PRICE = "Price"
    }

    fun addProduct(product:Product, cartQuantity: Int)
    {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, product._id)
        contentValues.put(KEY_PRODUCT_NAME, product.productName)
        contentValues.put(KEY_QUANTITY, cartQuantity)
        contentValues.put(KEY_PRICE, product.price)

        db.insert(TABLE_NAME, null, contentValues)


    }

    fun getProducts(){
        val db = this.readableDatabase
        //how to read rows from database using cursor
    }
}