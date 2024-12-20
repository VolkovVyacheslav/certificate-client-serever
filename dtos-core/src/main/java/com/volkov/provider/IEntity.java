package com.volkov.provider;

import java.io.Serializable;

public interface IEntity<ID extends Serializable>
             extends IdProvider<ID>{
}
