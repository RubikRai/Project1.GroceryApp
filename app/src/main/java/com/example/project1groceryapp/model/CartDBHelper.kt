package com.example.project1groceryapp.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.project1groceryapp.data.CartProduct
import com.example.project1groceryapp.data.Product

class CartDBHelper(private val context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 4) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CART_TABLE =
            ("CREATE TABLE " + TABLE_NAME + " ( " + KEY_ID + " VARCHAR PRIMARY KEY, "
                    + KEY_PRODUCT_NAME+ " VARCHAR, " + KEY_PRODUCT_IMAGE+ " VARCHAR, "+ KEY_QUANTITY + " INTEGER, " + KEY_PRICE + " INTEGER )")
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
        db.close()
    }

    fun addProduct(product:Product): Long {
        val productInCart = inCart(product)
        val db = this.writableDatabase

        if (productInCart == 0) {
            val contentValues = ContentValues()
            contentValues.put(KEY_ID, product._id)
            contentValues.put(KEY_PRODUCT_NAME, product.productName)
            contentValues.put(KEY_PRODUCT_IMAGE, product.image)
            contentValues.put(KEY_QUANTITY, 1)
            contentValues.put(KEY_PRICE, product.price)

            return db.insert(TABLE_NAME, null, contentValues)
            db.close()
        } else {
            return updateProduct(product, 1).toLong()
        }
    }

    private fun updateProduct(product: Product, addedQty: Int): Int {
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, product._id)
        contentValues.put(KEY_PRODUCT_NAME, product.productName)
        contentValues.put(KEY_PRODUCT_IMAGE, product.image)
        contentValues.put(KEY_QUANTITY, inCart(product) + addedQty)
        contentValues.put(KEY_PRICE, product.price)
        Log.d("Incart", inCart(product).toString())
        return db.update(TABLE_NAME, contentValues, """ID = '${product._id}'""",null)
        db.close()
    }

    fun inCart(product: Product):Int {
        val db = readableDatabase

        val productId = product._id
        val selectQuery = """SELECT * FROM $TABLE_NAME WHERE $KEY_ID = '$productId'"""
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            db.execSQL(selectQuery)
            return 0
        }

        var qtyInCart: Int = 0
        if(cursor.moveToFirst()){
            try{
                qtyInCart = cursor.getInt(cursor.getColumnIndexOrThrow("$KEY_QUANTITY"))
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
        return qtyInCart
    }


    fun getProducts(): ArrayList<CartProduct>{
        val db = this.readableDatabase
        val cart: ArrayList<CartProduct> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        var cursor: Cursor? = null

        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch(e:Exception){
            e.printStackTrace()
        }

        var productId: String
        var productName: String
        var productImage: String
        var productPrice: Float
        var productQuantity: Int

        if (cursor!!.moveToFirst()){
            do {
                productId = cursor.getString(cursor.getColumnIndexOrThrow("$KEY_ID"))
                productName = cursor.getString(cursor.getColumnIndexOrThrow("$KEY_PRODUCT_NAME"))
                productImage = cursor.getString(cursor.getColumnIndexOrThrow("$KEY_PRODUCT_IMAGE"))
                productPrice = cursor.getFloat(cursor.getColumnIndexOrThrow("$KEY_PRICE"))
                productQuantity = cursor.getInt(cursor.getColumnIndexOrThrow("$KEY_QUANTITY"))

                val product = CartProduct(productId,productName, productImage, productPrice, productQuantity)
                cart.add(product)
            }while (cursor.moveToNext())
        }
        return cart
    }


    fun deleteProduct(product: CartProduct): Int{
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, product._id)
        return db.delete(TABLE_NAME, "id = '${product._id}'", null)
    }



    companion object{
        const val DATABASE_NAME = "CartDB"
        const val TABLE_NAME = "PRODUCT"
        const val KEY_ID = "id"
        const val KEY_PRODUCT_IMAGE = "image"
        const val KEY_PRODUCT_NAME = "Name"
        const val KEY_QUANTITY =  "Quantity"
        const val KEY_PRICE = "Price"
    }
}