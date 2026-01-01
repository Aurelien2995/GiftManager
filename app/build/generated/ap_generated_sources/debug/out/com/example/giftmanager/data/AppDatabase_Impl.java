package com.example.giftmanager.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.giftmanager.data.dao.GiftDao;
import com.example.giftmanager.data.dao.GiftDao_Impl;
import com.example.giftmanager.data.dao.PersonDao;
import com.example.giftmanager.data.dao.PersonDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile PersonDao _personDao;

  private volatile GiftDao _giftDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Person` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `birthday` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `GiftIdea` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `personId` INTEGER NOT NULL, `title` TEXT, `description` TEXT, `price` REAL NOT NULL, `link` TEXT, `occasion` TEXT, `offered` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f1437b44c0d1a82f6c601d350d0ae188')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Person`");
        _db.execSQL("DROP TABLE IF EXISTS `GiftIdea`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsPerson = new HashMap<String, TableInfo.Column>(3);
        _columnsPerson.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPerson.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPerson.put("birthday", new TableInfo.Column("birthday", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPerson = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPerson = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPerson = new TableInfo("Person", _columnsPerson, _foreignKeysPerson, _indicesPerson);
        final TableInfo _existingPerson = TableInfo.read(_db, "Person");
        if (! _infoPerson.equals(_existingPerson)) {
          return new RoomOpenHelper.ValidationResult(false, "Person(com.example.giftmanager.data.entities.Person).\n"
                  + " Expected:\n" + _infoPerson + "\n"
                  + " Found:\n" + _existingPerson);
        }
        final HashMap<String, TableInfo.Column> _columnsGiftIdea = new HashMap<String, TableInfo.Column>(8);
        _columnsGiftIdea.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGiftIdea.put("personId", new TableInfo.Column("personId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGiftIdea.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGiftIdea.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGiftIdea.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGiftIdea.put("link", new TableInfo.Column("link", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGiftIdea.put("occasion", new TableInfo.Column("occasion", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGiftIdea.put("offered", new TableInfo.Column("offered", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGiftIdea = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGiftIdea = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGiftIdea = new TableInfo("GiftIdea", _columnsGiftIdea, _foreignKeysGiftIdea, _indicesGiftIdea);
        final TableInfo _existingGiftIdea = TableInfo.read(_db, "GiftIdea");
        if (! _infoGiftIdea.equals(_existingGiftIdea)) {
          return new RoomOpenHelper.ValidationResult(false, "GiftIdea(com.example.giftmanager.data.entities.GiftIdea).\n"
                  + " Expected:\n" + _infoGiftIdea + "\n"
                  + " Found:\n" + _existingGiftIdea);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "f1437b44c0d1a82f6c601d350d0ae188", "af4055239234c12a441d06df49cff75c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Person","GiftIdea");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Person`");
      _db.execSQL("DELETE FROM `GiftIdea`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(PersonDao.class, PersonDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GiftDao.class, GiftDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public PersonDao personDao() {
    if (_personDao != null) {
      return _personDao;
    } else {
      synchronized(this) {
        if(_personDao == null) {
          _personDao = new PersonDao_Impl(this);
        }
        return _personDao;
      }
    }
  }

  @Override
  public GiftDao giftDao() {
    if (_giftDao != null) {
      return _giftDao;
    } else {
      synchronized(this) {
        if(_giftDao == null) {
          _giftDao = new GiftDao_Impl(this);
        }
        return _giftDao;
      }
    }
  }
}
