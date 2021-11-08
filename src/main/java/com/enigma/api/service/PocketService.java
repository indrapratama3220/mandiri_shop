package com.enigma.api.service;

import com.enigma.api.entity.Pocket;

import java.util.Set;

public interface PocketService {
    public Pocket getPocketById(String id);

    public Pocket savePocket(Pocket pocket);

    public Pocket editPocket(Pocket pocket);

    public void deletePocket(String id);

    public Set<Pocket> customerPockets(String id, Integer idProduct);
}
