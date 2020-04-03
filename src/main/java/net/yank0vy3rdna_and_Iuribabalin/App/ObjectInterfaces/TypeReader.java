package net.yank0vy3rdna_and_Iuribabalin.App.ObjectInterfaces;

import net.yank0vy3rdna_and_Iuribabalin.App.UI;

import java.io.Serializable;

public interface TypeReader {
    Serializable create(String id);
    void setUI(UI ui);
}
