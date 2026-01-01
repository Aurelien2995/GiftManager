package com.example.giftmanager.data.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.giftmanager.data.entities.GiftIdea;
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
public final class GiftDao_Impl implements GiftDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<GiftIdea> __insertionAdapterOfGiftIdea;

  private final EntityDeletionOrUpdateAdapter<GiftIdea> __deletionAdapterOfGiftIdea;

  private final EntityDeletionOrUpdateAdapter<GiftIdea> __updateAdapterOfGiftIdea;

  public GiftDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGiftIdea = new EntityInsertionAdapter<GiftIdea>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `GiftIdea` (`id`,`personId`,`title`,`description`,`price`,`link`,`occasion`,`offered`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GiftIdea value) {
        stmt.bindLong(1, value.id);
        stmt.bindLong(2, value.personId);
        if (value.title == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.title);
        }
        if (value.description == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.description);
        }
        stmt.bindDouble(5, value.price);
        if (value.link == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.link);
        }
        if (value.occasion == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.occasion);
        }
        final int _tmp = value.offered ? 1 : 0;
        stmt.bindLong(8, _tmp);
      }
    };
    this.__deletionAdapterOfGiftIdea = new EntityDeletionOrUpdateAdapter<GiftIdea>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `GiftIdea` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GiftIdea value) {
        stmt.bindLong(1, value.id);
      }
    };
    this.__updateAdapterOfGiftIdea = new EntityDeletionOrUpdateAdapter<GiftIdea>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `GiftIdea` SET `id` = ?,`personId` = ?,`title` = ?,`description` = ?,`price` = ?,`link` = ?,`occasion` = ?,`offered` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GiftIdea value) {
        stmt.bindLong(1, value.id);
        stmt.bindLong(2, value.personId);
        if (value.title == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.title);
        }
        if (value.description == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.description);
        }
        stmt.bindDouble(5, value.price);
        if (value.link == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.link);
        }
        if (value.occasion == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.occasion);
        }
        final int _tmp = value.offered ? 1 : 0;
        stmt.bindLong(8, _tmp);
        stmt.bindLong(9, value.id);
      }
    };
  }

  @Override
  public long insert(final GiftIdea gift) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfGiftIdea.insertAndReturnId(gift);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final GiftIdea gift) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfGiftIdea.handle(gift);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final GiftIdea gift) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfGiftIdea.handle(gift);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<GiftIdea> getForPerson(final int personId) {
    final String _sql = "SELECT * FROM GiftIdea WHERE personId = ? ORDER BY offered, title";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, personId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPersonId = CursorUtil.getColumnIndexOrThrow(_cursor, "personId");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfLink = CursorUtil.getColumnIndexOrThrow(_cursor, "link");
      final int _cursorIndexOfOccasion = CursorUtil.getColumnIndexOrThrow(_cursor, "occasion");
      final int _cursorIndexOfOffered = CursorUtil.getColumnIndexOrThrow(_cursor, "offered");
      final List<GiftIdea> _result = new ArrayList<GiftIdea>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final GiftIdea _item;
        final int _tmpPersonId;
        _tmpPersonId = _cursor.getInt(_cursorIndexOfPersonId);
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        final String _tmpLink;
        if (_cursor.isNull(_cursorIndexOfLink)) {
          _tmpLink = null;
        } else {
          _tmpLink = _cursor.getString(_cursorIndexOfLink);
        }
        final String _tmpOccasion;
        if (_cursor.isNull(_cursorIndexOfOccasion)) {
          _tmpOccasion = null;
        } else {
          _tmpOccasion = _cursor.getString(_cursorIndexOfOccasion);
        }
        final boolean _tmpOffered;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfOffered);
        _tmpOffered = _tmp != 0;
        _item = new GiftIdea(_tmpPersonId,_tmpTitle,_tmpDescription,_tmpPrice,_tmpLink,_tmpOccasion,_tmpOffered);
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
  public GiftIdea getById(final int id) {
    final String _sql = "SELECT * FROM GiftIdea WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfPersonId = CursorUtil.getColumnIndexOrThrow(_cursor, "personId");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfLink = CursorUtil.getColumnIndexOrThrow(_cursor, "link");
      final int _cursorIndexOfOccasion = CursorUtil.getColumnIndexOrThrow(_cursor, "occasion");
      final int _cursorIndexOfOffered = CursorUtil.getColumnIndexOrThrow(_cursor, "offered");
      final GiftIdea _result;
      if(_cursor.moveToFirst()) {
        final int _tmpPersonId;
        _tmpPersonId = _cursor.getInt(_cursorIndexOfPersonId);
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final double _tmpPrice;
        _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
        final String _tmpLink;
        if (_cursor.isNull(_cursorIndexOfLink)) {
          _tmpLink = null;
        } else {
          _tmpLink = _cursor.getString(_cursorIndexOfLink);
        }
        final String _tmpOccasion;
        if (_cursor.isNull(_cursorIndexOfOccasion)) {
          _tmpOccasion = null;
        } else {
          _tmpOccasion = _cursor.getString(_cursorIndexOfOccasion);
        }
        final boolean _tmpOffered;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfOffered);
        _tmpOffered = _tmp != 0;
        _result = new GiftIdea(_tmpPersonId,_tmpTitle,_tmpDescription,_tmpPrice,_tmpLink,_tmpOccasion,_tmpOffered);
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
