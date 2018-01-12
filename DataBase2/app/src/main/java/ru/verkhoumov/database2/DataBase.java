package ru.verkhoumov.database2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBase extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "contacts_v2";
    public static final String ID_FIELD = "_id";
    public static final String FIRST_NAME_FIELD = "first_name";
    public static final String LAST_NAME_FIELD = "last_name";
    public static final String PHONE_FIELD = "phone";
    public static final String EMAIL_FIELD = "email";

    public DataBase(Context context) {
        super(context, "contacts_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("db", "onCreate");

        String sql = "CREATE TABLE IF NOT EXISTS `" + TABLE_NAME + "`"
                + " (`" + ID_FIELD + "` INTEGER, `"
                + FIRST_NAME_FIELD + "` TEXT, `"
                + LAST_NAME_FIELD + "` TEXT, `"
                + PHONE_FIELD + "` TEXT, `"
                + EMAIL_FIELD + "` TEXT, PRIMARY KEY (`"
                + ID_FIELD + "`));";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("db", "onUpgrade");

        // Пересоздание таблицы.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS `" + TABLE_NAME + "`");
        onCreate(sqLiteDatabase);
    }

    public Contact addContact(Contact contact) {
        Log.d("db", "addContacts");

        // Формируем новую запись для последующей вставки в базу данных.
        ContentValues row = new ContentValues();
        row.put(FIRST_NAME_FIELD, contact.getFirstName());
        row.put(LAST_NAME_FIELD, contact.getLastName());
        row.put(PHONE_FIELD, contact.getPhone());
        row.put(EMAIL_FIELD, contact.getEmail());

        // Добавляем новую запись в базу данных.
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long id = sqLiteDatabase.insert(TABLE_NAME, null, row);

        // Закрываем текущее соединение.
        sqLiteDatabase.close();

        contact.setId(id);

        return contact;
    }

    public Contact getContact(long id) {
        Log.d("db", "getContact");

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(
                TABLE_NAME,
                new String[] { ID_FIELD, FIRST_NAME_FIELD, LAST_NAME_FIELD, PHONE_FIELD, EMAIL_FIELD },
                "`" + ID_FIELD + "`" + " = ?",
                new String[] { String.valueOf(id) },
                null, null, null, null
        );

        if (cursor != null) {
            cursor.moveToFirst();

            // NOTE: В коде задания были указаны сразу индексы полей. Почему?
            Contact contact = new Contact(
                    cursor.getString(cursor.getColumnIndex(FIRST_NAME_FIELD)),
                    cursor.getString(cursor.getColumnIndex(LAST_NAME_FIELD)),
                    cursor.getString(cursor.getColumnIndex(PHONE_FIELD)),
                    cursor.getString(cursor.getColumnIndex(EMAIL_FIELD))
            );

            // ID записи.
            contact.setId(cursor.getLong(cursor.getColumnIndex(ID_FIELD)));

            return contact;
        }

        return null;
    }

    public Cursor getContactsCursor() {
        Log.d("db", "getContactsCursor");

        // NOTE: В коде задания берётся getWritableDatabase, хотя необходимо только чтение данных.
        String sql = "SELECT * FROM `" + TABLE_NAME + "`";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        return sqLiteDatabase.rawQuery(sql, null);
    }

    public void deleteContact(long id) {
        Log.d("db", "deleteContact");

        // Удаляем запись из базы данных.
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(
                TABLE_NAME,
                "`" + ID_FIELD + "`" + " = ?",
                new String[] { String.valueOf(id) }
        );

        // Закрываем текущее соединение.
        sqLiteDatabase.close();
    }
}
