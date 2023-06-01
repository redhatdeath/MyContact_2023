package ru.shanin.app;

import android.app.Application;

import ru.shanin.data.db_room.database.AppDatabase;
import ru.shanin.data.repositoryImpl.RepositoryWorkImpl;
import ru.shanin.domain.usecases.AddNewUseCases;
import ru.shanin.domain.usecases.DeleteByIdUseCase;
import ru.shanin.domain.usecases.EditByIdUseCase;
import ru.shanin.domain.usecases.GetByAllUseCase;
import ru.shanin.domain.usecases.GetByIdUseCase;

public class AppStart extends Application {
    private AddNewUseCases addItem;
    private GetByIdUseCase getOneItem;
    private EditByIdUseCase editItem;
    private DeleteByIdUseCase removeItem;
    private GetByAllUseCase getListItems;
    private static AppStart instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        RepositoryWorkImpl repositoryImpl = new RepositoryWorkImpl(db.personDao());
        addItem = new AddNewUseCases(repositoryImpl);
        getOneItem = new GetByIdUseCase(repositoryImpl);
        editItem = new EditByIdUseCase(repositoryImpl);
        removeItem = new DeleteByIdUseCase(repositoryImpl);
        getListItems = new GetByAllUseCase(repositoryImpl);
    }

    public AddNewUseCases getAddItem() {
        return addItem;
    }

    public GetByIdUseCase getGetOneItem() {
        return getOneItem;
    }

    public EditByIdUseCase getEditItem() {
        return editItem;
    }

    public DeleteByIdUseCase getRemoveItem() {
        return removeItem;
    }

    public GetByAllUseCase getGetListItems() {
        return getListItems;
    }

    public static AppStart getInstance() {
        return instance;
    }
}