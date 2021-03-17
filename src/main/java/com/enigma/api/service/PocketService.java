package com.enigma.api.service;

import com.enigma.api.entity.Pocket;

public interface PocketService {
    public Pocket getPocketById(String id);
    public Pocket savePocket(Pocket balance);
}
