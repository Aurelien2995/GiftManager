package com.example.giftmanager.data.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.giftmanager.data.entities.Person;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PersonDao_Impl implements PersonDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Person> __insertionAdapterOfPerson;

  private final EntityDeletionOrUpdateAdapter<Person> __deletionAdapterOfPerson;

  private final EntityDeletionOrUpdateAdapter<Person> __updateAdapterOfPerson;

  public PersonDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPerson = new EntityInsertionAdapter<Person>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Person` (`id`,`name`,`birthday`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Person value) {
        stmt.bindLong(1, value.id);
        if (value.name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.name);
        }
        if (value.birthday == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.birthday);
        }
      }
    };
    this.__deletionAdapterOfPerson = new EntityDeletionOrUpdateAdapter<Person>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Person` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Person value) {
        stmt.bindLong(1, value.id);
      }
    };
    this.__updateAdapterOfPerson = new EntityDeletionOrUpdateAdapter<Person>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Person` SET `id` = ?,`name` = ?,`birthday` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Person value) {
        stmt.bindLong(1, value.id);
        if (value.name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.name);
        }
        if (value.birthday == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.birthday);
        }
        stmt.bindLong(4, value.id);
      }
    };
  }

  @Override
  public long insert(final Person person) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfPerson.insertAndReturnId(person);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Person person) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfPerson.handle(person);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Person person) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfPerson.handle(person);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Person> getAll() {
    final String _sql = "SELECT * FROM Person ORDER BY name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfBirthday = CursorUtil.getColumnIndexOrThrow(_cursor, "birthday");
      final List<Person> _result = new ArrayList<Person>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Person _item;
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpBirthday;
        if (_cursor.isNull(_cursorIndexOfBirthday)) {
          _tmpBirthday = null;
        } else {
          _tmpBirthday = _cursor.getString(_cursorIndexOfBirthday);
        }
        _item = new Person(_tmpName,_tmpBirthday);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Person getById(final int id) {
    final String _sql = "SELECT * FROM Person WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfBirthday = CursorUtil.getColumnIndexOrThrow(_cursor, "birthday");
      final Person _result;
      if(_cursor.moveToFirst()) {
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpBirthday;
        if (_cursor.isNull(_cursorIndexOfBirthday)) {
          _tmpBirthday = null;
        } else {
          _tmpBirthday = _cursor.getString(_cursorIndexOfBirthday);
        }
        _result = new Person(_tmpName,_tmpBirthday);
        _result.id = _cursor.getInt(_cursorIndexOfId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
