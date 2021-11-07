package dev.cerus.advance.util;

import dev.cerus.advance.api.AdvanceApi;
import org.bukkit.Bukkit;

public class ApiFactory {

    public AdvanceApi makeApi() {
        String version = Bukkit.getVersion();
        version = version.substring(version.indexOf("MC: ") + 4, version.lastIndexOf(')'));

        switch (version) {
            case "1.17":
            case "1.17.1":
                return new dev.cerus.advance.api.v17r1.AdvanceApiImpl();
            case "1.16.5":
                return new dev.cerus.advance.api.v16r3.AdvanceApiImpl();
            default:
                return null;
        }
    }

}
