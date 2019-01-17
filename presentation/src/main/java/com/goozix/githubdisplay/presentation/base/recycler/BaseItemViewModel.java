package com.goozix.githubdisplay.presentation.base.recycler;

import com.goozix.domain.entity.DomainModel;

public abstract class BaseItemViewModel<Entity extends DomainModel> {

    public abstract void setItem(Entity entity, int position);

    public void onItemClick(){

    }
}
