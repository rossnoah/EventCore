/*
 * Copyright 2023 Noah Ross
 *
 * This file is part of EventCore.
 *
 * EventCore is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * EventCore is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with EventCore. If not, see <https://www.gnu.org/licenses/>.
 */
package dev.noah.eventcore.util;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class InvUtil {

    public static Inventory addItem(Inventory inv,int slot,Material material,int quantity,String name,boolean enchanted){
        ItemStack item = new ItemStack(material,quantity);
        if(enchanted){
            ItemUtil.addEnchantLook(item);
        }
        if(!name.equalsIgnoreCase("false")) {
            ItemUtil.setName(item,name);
        }
        inv.setItem(slot,item);
        return  inv;
    }

    public static Inventory toggle(Inventory inv,int slot){

        if(Objects.requireNonNull(inv.getItem(slot)).getType()==Material.RED_CONCRETE_POWDER&& Objects.requireNonNull(inv.getItem(slot)).getAmount()==1){
            addItem(inv,slot,Material.LIME_CONCRETE_POWDER,1, Objects.requireNonNull(Objects.requireNonNull(inv.getItem(slot)).getItemMeta()).getDisplayName(),false);
            return inv;
        }else if(Objects.requireNonNull(inv.getItem(slot)).getType()==Material.LIME_CONCRETE_POWDER&& Objects.requireNonNull(inv.getItem(slot)).getAmount()==1){
                addItem(inv,slot,Material.RED_CONCRETE_POWDER,1, Objects.requireNonNull(Objects.requireNonNull(inv.getItem(slot)).getItemMeta()).getDisplayName(),false);
           return inv;
            }
        return inv;

    }

    }


