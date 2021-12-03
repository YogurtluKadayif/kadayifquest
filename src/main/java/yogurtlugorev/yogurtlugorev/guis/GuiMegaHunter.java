package yogurtlugorev.yogurtlugorev.guis;

import com.google.common.collect.Iterables;
import com.pixelmonmod.pixelmon.config.PixelmonItemsHeld;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.*;
import org.spongepowered.api.item.inventory.property.InventoryDimension;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.item.inventory.property.SlotPos;
import org.spongepowered.api.item.inventory.transaction.SlotTransaction;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import yogurtlugorev.yogurtlugorev.Yogurtlugorev;

import java.sql.*;
import java.util.Arrays;
import java.util.UUID;

public class GuiMegaHunter {
    public Inventory inventory;
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./kadayifdb/kadayifquest";
    static final String USER = "sa";
    static final String PASS = "";
    Connection connect = null;
    Statement statmt = null;
    Statement statmt2 = null;

    public GuiMegaHunter(Player player) {
        this.inventory = Inventory.builder()
                .of(InventoryArchetypes.DOUBLE_CHEST)
                .property(InventoryTitle.PROPERTY_NAME, InventoryTitle.of(Text.of(TextColors.AQUA, "KadayifQuest")))
                .property("inventorydimension", InventoryDimension.of(9, 1))
                .listener(ClickInventoryEvent.class, (ClickInventoryEvent event) -> {
                    event.setCancelled(true);
                    ItemStack affected;
                    for (SlotTransaction slotTransaction : event.getTransactions()) {
                        Slot slot = slotTransaction.getSlot();
                        Container container = event.getTargetInventory();
                        SlotIndex pos = Iterables.getOnlyElement(slot.parent().getProperties(slot, SlotIndex.class));
                        affected = event.getTransactions().get(0).getOriginal().createStack();
                        final UUID uuid = player.getUniqueId();
                        /* Acemi Mega Avcisi */
                        if (affected.getType() == PixelmonItemsHeld.abomasite) {
                            player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.RED, "Bu gorev suanda bozuktur, /quest abandon kullanarak vazgeciniz."));
                            try {
                                connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                statmt = connect.createStatement();
                                try {
                                    statmt = connect.createStatement();
                                    String sqlara = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                                    ResultSet rsara = statmt.executeQuery(sqlara);
                                    if (rsara.next()) {
                                        if (rsara.getString("megaavcisi").equals("baslamadi")) {
                                            if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                if(rsara.getString("gorev1").equals("yok")) {
                                                    String sql = "UPDATE Gorev SET megaavcisi=?, gorev1='megaavcisi', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                    PreparedStatement ps = connect.prepareStatement(sql);
                                                    ps.setString(1, "g1devam");
                                                    ps.setInt(2, 0);
                                                    ps.executeUpdate();
                                                    Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                    player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Acemi Mega Avcisi gorevine basladiniz."));
                                                } else {
                                                    if(rsara.getString("gorev2").equals("yok")) {
                                                        String sql = "UPDATE Gorev SET megaavcisi=?, gorev2='megaavcisi', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                        PreparedStatement ps = connect.prepareStatement(sql);
                                                        ps.setString(1, "g1devam");
                                                        ps.setInt(2, 0);
                                                        ps.executeUpdate();
                                                        Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                        player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Acemi Mega Avcisi gorevine basladiniz."));
                                                    }
                                                }
                                            } else {
                                                player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "2 gorevden fazlasini alamazsiniz."));
                                            }
                                        } else {
                                            player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Bu gorev devam ediyor veya zaten bitirdiniz."));
                                        }
                                    } else {
                                        player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Hata."));
                                    }
                                } catch (SQLException se) {
                                    se.printStackTrace();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    try {
                                        if (statmt != null) statmt.close();
                                    } catch (SQLException se2) {
                                    }
                                    try {
                                        if (connect != null) connect.close();
                                    } catch (SQLException se) {
                                        se.printStackTrace();
                                    }
                                }
                            } catch (SQLException se) {
                                se.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            /* Tecrübeli Mega Avcisi */
                            if (affected.getType() == PixelmonItemsHeld.charizardite_y) {
                                player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.RED, "Bu gorev suanda bozuktur, /quest abandon kullanarak vazgeciniz."));
                                try {
                                    connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                    statmt = connect.createStatement();
                                    try {
                                        statmt = connect.createStatement();
                                        String sqlara = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                                        ResultSet rsara = statmt.executeQuery(sqlara);
                                        if (rsara.next()) {
                                            if (rsara.getString("megaavcisi").equals("g1bitti")) {
                                                if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                    if(rsara.getString("gorev1").equals("yok")) {
                                                        String sql = "UPDATE Gorev SET megaavcisi=?, gorev1='megaavcisi', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                        PreparedStatement ps = connect.prepareStatement(sql);
                                                        ps.setString(1, "g2devam");
                                                        ps.setInt(2, 0);
                                                        ps.executeUpdate();
                                                        Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                        player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Tecrubeli Mega Avcisi gorevine basladiniz."));
                                                    } else {
                                                        if(rsara.getString("gorev2").equals("yok")) {
                                                            String sql = "UPDATE Gorev SET megaavcisi=?, gorev2='megaavcisi', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                            PreparedStatement ps = connect.prepareStatement(sql);
                                                            ps.setString(1, "g2devam");
                                                            ps.setInt(2, 0);
                                                            ps.executeUpdate();
                                                            Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                            player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Tecrubeli Mega Avcisi gorevine basladiniz."));
                                                        }
                                                    }
                                                } else {
                                                    player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "2 gorevden fazlasini alamazsiniz."));
                                                }
                                            } else {
                                                player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Bu gorev devam ediyor, onceki gorevdesiniz veya zaten bitirdiniz."));
                                            }
                                        } else {
                                            player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Hata."));
                                        }
                                    } catch (SQLException se) {
                                        se.printStackTrace();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    } finally {
                                        try {
                                            if (statmt != null) statmt.close();
                                        } catch (SQLException se2) {
                                        }
                                        try {
                                            if (connect != null) connect.close();
                                        } catch (SQLException se) {
                                            se.printStackTrace();
                                        }
                                    }
                                } catch (SQLException se) {
                                    se.printStackTrace();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                /* Bilgili Mega Avcisi */
                                if (affected.getType() == PixelmonItemsHeld.mawilite) {
                                    player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.RED, "Bu gorev suanda bozuktur, /quest abandon kullanarak vazgeciniz."));
                                    try {
                                        connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                        statmt = connect.createStatement();
                                        try {
                                            statmt = connect.createStatement();
                                            String sqlara = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                                            ResultSet rsara = statmt.executeQuery(sqlara);
                                            if (rsara.next()) {
                                                if (rsara.getString("megaavcisi").equals("g2bitti")) {
                                                    if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                        if(rsara.getString("gorev1").equals("yok")) {
                                                            String sql = "UPDATE Gorev SET megaavcisi=?, gorev1='megaavcisi', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                            PreparedStatement ps = connect.prepareStatement(sql);
                                                            ps.setString(1, "g3devam");
                                                            ps.setInt(2, 0);
                                                            ps.executeUpdate();
                                                            Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                            player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Bilgili Mega Avcisi gorevine basladiniz."));
                                                        } else {
                                                            if(rsara.getString("gorev2").equals("yok")) {
                                                                String sql = "UPDATE Gorev SET megaavcisi=?, gorev2='megaavcisi', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                                PreparedStatement ps = connect.prepareStatement(sql);
                                                                ps.setString(1, "g3devam");
                                                                ps.setInt(2, 0);
                                                                ps.executeUpdate();
                                                                Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                                player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Bilgili Mega Avcisi gorevine basladiniz."));
                                                            }
                                                        }
                                                    } else {
                                                        player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "2 gorevden fazlasini alamazsiniz."));
                                                    }
                                                } else {
                                                    player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Bu gorev devam ediyor, onceki gorevdesiniz veya zaten bitirdiniz."));
                                                }
                                            } else {
                                                player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Hata."));
                                            }
                                        } catch (SQLException se) {
                                            se.printStackTrace();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        } finally {
                                            try {
                                                if (statmt != null) statmt.close();
                                            } catch (SQLException se2) {
                                            }
                                            try {
                                                if (connect != null) connect.close();
                                            } catch (SQLException se) {
                                                se.printStackTrace();
                                            }
                                        }
                                    } catch (SQLException se) {
                                        se.printStackTrace();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    /* Usta Mega Avcisi */
                                    if (affected.getType() == PixelmonItemsHeld.mewtwonite_y) {
                                        player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.RED, "Bu gorev suanda bozuktur, /quest abandon kullanarak vazgeciniz."));
                                        try {
                                            connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                            statmt = connect.createStatement();
                                            try {
                                                statmt = connect.createStatement();
                                                String sqlara = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                                                ResultSet rsara = statmt.executeQuery(sqlara);
                                                if (rsara.next()) {
                                                    if (rsara.getString("megaavcisi").equals("g3bitti")) {
                                                        if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                            if(rsara.getString("gorev1").equals("yok")) {
                                                                String sql = "UPDATE Gorev SET megaavcisi=?, gorev1='megaavcisi', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                                PreparedStatement ps = connect.prepareStatement(sql);
                                                                ps.setString(1, "g4devam");
                                                                ps.setInt(2, 0);
                                                                ps.executeUpdate();
                                                                Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                                player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Usta Mega Avcisi gorevine basladiniz."));
                                                            } else {
                                                                if(rsara.getString("gorev2").equals("yok")) {
                                                                    String sql = "UPDATE Gorev SET megaavcisi=?, gorev2='megaavcisi', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                                    PreparedStatement ps = connect.prepareStatement(sql);
                                                                    ps.setString(1, "g4devam");
                                                                    ps.setInt(2, 0);
                                                                    ps.executeUpdate();
                                                                    Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                                    player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Usta Mega Avcisi gorevine basladiniz."));
                                                                }
                                                            }
                                                        } else {
                                                            player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "2 gorevden fazlasini alamazsiniz."));
                                                        }
                                                    } else {
                                                        player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Bu gorev devam ediyor, onceki gorevdesiniz veya zaten bitirdiniz."));
                                                    }
                                                } else {
                                                    player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Hata."));
                                                }
                                            } catch (SQLException se) {
                                                se.printStackTrace();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            } finally {
                                                try {
                                                    if (statmt != null) statmt.close();
                                                } catch (SQLException se2) {
                                                }
                                                try {
                                                    if (connect != null) connect.close();
                                                } catch (SQLException se) {
                                                    se.printStackTrace();
                                                }
                                            }
                                        } catch (SQLException se) {
                                            se.printStackTrace();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    }
                })
                .build(Yogurtlugorev.instance);
        try {
            connect = DriverManager.getConnection(DB_URL, USER, PASS);
            statmt2 = connect.createStatement();

            try {
                statmt2 = connect.createStatement();

                String sqlara = "SELECT * FROM Gorev WHERE oyuncu='"+ player.getName() + "'";
                ResultSet rsara = statmt2.executeQuery(sqlara);

                /*inventory.query(SlotPos.of(0, 0)).set(ItemStack.builder()
                        .from(ItemStack.of(ItemTypes.STAINED_GLASS_PANE))
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "kadayifQuest"))
                        .add(Keys.ITEM_LORE, Arrays.asList(
                                Text.of(TextColors.AQUA, "--KadayifQuest--")))
                        .build());
                inventory.query(SlotPos.of(2, 0)).set(ItemStack.builder()
                        .from(ItemStack.of(ItemTypes.STAINED_GLASS_PANE))
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "kadayifQuest"))
                        .add(Keys.ITEM_LORE, Arrays.asList(
                                Text.of(TextColors.AQUA, "--KadayifQuest--")))
                        .build());

                inventory.query(SlotPos.of(4, 0)).set(ItemStack.builder()
                        .from(ItemStack.of(ItemTypes.STAINED_GLASS_PANE))
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "kadayifQuest"))
                        .add(Keys.ITEM_LORE, Arrays.asList(
                                Text.of(TextColors.AQUA, "--KadayifQuest--")))
                        .build());
                inventory.query(SlotPos.of(6, 0)).set(ItemStack.builder()
                        .from(ItemStack.of(ItemTypes.STAINED_GLASS_PANE))
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "kadayifQuest"))
                        .add(Keys.ITEM_LORE, Arrays.asList(
                                Text.of(TextColors.AQUA, "--KadayifQuest--")))
                        .build());
                inventory.query(SlotPos.of(8, 0)).set(ItemStack.builder()
                        .from(ItemStack.of(ItemTypes.STAINED_GLASS_PANE))
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "kadayifQuest"))
                        .add(Keys.ITEM_LORE, Arrays.asList(
                                Text.of(TextColors.AQUA, "--KadayifQuest--")))
                        .build());*/

                if(rsara.next()) {
                    // başlamadı
                    if(rsara.getString("megaavcisi").equals("baslamadi")) {
                        setItem(1,0,1,"baslamadi","abomasite");
                        setItem(3,0,0,"baslamadi","charizarditey");
                        setItem(5,0,0,"baslamadi","mawilite");
                        setItem(7,0,0,"baslamadi","mewtwonitey");
                    } else {
                        // Acemi Mega Avcisi - devam ediyor
                        if(rsara.getString("megaavcisi").equals("g1devam")) {
                            setItem(1,0,1,"basladi","abomasite");
                            setItem(3,0,0,"baslamadi","charizarditey");
                            setItem(5,0,0,"baslamadi","mawilite");
                            setItem(7,0,0,"baslamadi","mewtwonitey");
                        } else {
                            // Acemi Mega Avcisi bitti
                            if(rsara.getString("megaavcisi").equals("g1bitti")) {
                                setItem(1,0,1,"bitirdi","abomasite");
                                setItem(3,0,1,"baslamadi","charizarditey");
                                setItem(5,0,0,"baslamadi","mawilite");
                                setItem(7,0,0,"baslamadi","mewtwonitey");
                            } else {
                                // Tecrübeli Mega Avcisi devam ediyor
                                if(rsara.getString("megaavcisi").equals("g2devam")) {
                                    setItem(1,0,1,"bitirdi","abomasite");
                                    setItem(3,0,1,"basladi","charizarditey");
                                    setItem(5,0,0,"baslamadi","mawilite");
                                    setItem(7,0,0,"baslamadi","mewtwonitey");
                                } else {
                                    // Tecrübeli Mega Avcisi bitti
                                    if(rsara.getString("megaavcisi").equals("g2bitti")) {
                                        setItem(1,0,1,"bitirdi","abomasite");
                                        setItem(3,0,1,"bitirdi","charizarditey");
                                        setItem(5,0,1,"baslamadi","mawilite");
                                        setItem(7,0,0,"baslamadi","mewtwonitey");
                                    } else {
                                        // Bilgili Mega Avcisi devam ediyor
                                        if(rsara.getString("megaavcisi").equals("g3devam")) {
                                            setItem(1,0,1,"bitirdi","abomasite");
                                            setItem(3,0,1,"bitirdi","charizarditey");
                                            setItem(5,0,1,"basladi","mawilite");
                                            setItem(7,0,0,"baslamadi","mewtwonitey");
                                        } else {
                                            // Bilgili Mega Avcisi bitti
                                            if(rsara.getString("megaavcisi").equals("g3bitti")) {
                                                setItem(1,0,1,"bitirdi","abomasite");
                                                setItem(3,0,1,"bitirdi","charizarditey");
                                                setItem(5,0,1,"bitirdi","mawilite");
                                                setItem(7,0,1,"baslamadi","mewtwonitey");
                                            } else {
                                                // Usta Mega Avcisi devam ediyor
                                                if(rsara.getString("megaavcisi").equals("g4devam")) {
                                                    setItem(1,0,1,"bitirdi","abomasite");
                                                    setItem(3,0,1,"bitirdi","charizarditey");
                                                    setItem(5,0,1,"bitirdi","mawilite");
                                                    setItem(7,0,1,"basladi","mewtwonitey");
                                                } else {
                                                    // Usta Mega Avcisi bitti
                                                    if(rsara.getString("megaavcisi").equals("g4bitti")) {
                                                        setItem(1,0,1,"bitirdi","abomasite");
                                                        setItem(3,0,1,"bitirdi","charizarditey");
                                                        setItem(5,0,1,"bitirdi","mawilite");
                                                        setItem(7,0,1,"bitirdi","mewtwonitey");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


            } catch (SQLException se) {
                se.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (statmt != null) statmt.close();
                } catch (SQLException se2) {
                }
                try {
                    if (connect != null) connect.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setItem(Integer x, Integer y, Integer visin, String gorevdurum, String item) {
        // x,y koordinat; visin görünür görünmez(??? || isim); ilerleme ilerleme; gorevdurum basladi baslamadi bitirdi; item item;
        /* Acemi Mega Avcisi */
        if(item.equals("abomasite")) {
            if(visin == 1) {
                if(gorevdurum.equals("basladi")) {
                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                            .from(ItemStack.of((ItemType) PixelmonItemsHeld.abomasite))
                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Acemi Mega Avcisi"))
                            .add(Keys.ITEM_LORE, Arrays.asList(
                                    Text.of(TextColors.AQUA, "3 mega boss pokemon oldur."),
                                    Text.of(TextColors.GREEN, "Oduller:"),
                                    Text.of(TextColors.BLUE, "500$"),
                                    Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                            .build());
                } else {
                    if(gorevdurum.equals("baslamadi")) {
                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                .from(ItemStack.of((ItemType) PixelmonItemsHeld.abomasite))
                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Acemi Mega Avcisi"))
                                .add(Keys.ITEM_LORE, Arrays.asList(
                                        Text.of(TextColors.AQUA, "3 mega boss pokemon oldur."),
                                        Text.of(TextColors.GREEN, "Oduller:"),
                                        Text.of(TextColors.BLUE, "500$"),
                                        Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                .build());
                    } else {
                        if(gorevdurum.equals("bitirdi")) {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) PixelmonItemsHeld.abomasite))
                                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Acemi Mega Avcisi"))
                                    .add(Keys.ITEM_LORE, Arrays.asList(
                                            Text.of(TextColors.AQUA, "3 boss pokemon oldur."),
                                            Text.of(TextColors.GREEN, "Oduller:"),
                                            Text.of(TextColors.BLUE, "500$"),
                                            Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                    .build());
                        }
                    }
                }
            } else {
                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                        .from(ItemStack.of((ItemType) PixelmonItemsHeld.abomasite))
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "???"))
                        .add(Keys.ITEM_LORE, Arrays.asList(
                                Text.of(TextColors.AQUA, "?"),
                                Text.of(TextColors.GREEN, "?"),
                                Text.of(TextColors.BLUE, "?"),
                                Text.of(TextColors.YELLOW, "Goreve baslamak icin onceki gorevi bitir.")))
                        .build());
            }
        } else {
            /* Tecrübeli Mega Avcisi */
            if(item.equals("charizarditey")) {
                if(visin == 1) {
                    if(gorevdurum.equals("basladi")) {
                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                .from(ItemStack.of((ItemType) PixelmonItemsHeld.charizardite_y))
                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "Tecrubeli Mega Avcisi"))
                                .add(Keys.ITEM_LORE, Arrays.asList(
                                        Text.of(TextColors.AQUA, "10 boss pokemon oldur."),
                                        Text.of(TextColors.GREEN, "Oduller:"),
                                        Text.of(TextColors.BLUE, "1.000$"),
                                        Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                                .build());
                    } else {
                        if(gorevdurum.equals("baslamadi")) {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) PixelmonItemsHeld.charizardite_y))
                                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "Tecrubeli Mega Avcisi"))
                                    .add(Keys.ITEM_LORE, Arrays.asList(
                                            Text.of(TextColors.AQUA, "10 boss pokemon oldur."),
                                            Text.of(TextColors.GREEN, "Oduller:"),
                                            Text.of(TextColors.BLUE, "1.000$"),
                                            Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                    .build());
                        } else {
                            if(gorevdurum.equals("bitirdi")) {
                                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                        .from(ItemStack.of((ItemType) PixelmonItemsHeld.charizardite_y))
                                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "Tecrubeli Mega Avcisi"))
                                        .add(Keys.ITEM_LORE, Arrays.asList(
                                                Text.of(TextColors.AQUA, "10 boss pokemon oldur."),
                                                Text.of(TextColors.GREEN, "Oduller:"),
                                                Text.of(TextColors.BLUE, "1.000$"),
                                                Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                        .build());
                            }
                        }
                    }
                } else {
                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                            .from(ItemStack.of((ItemType) PixelmonItemsHeld.charizardite_y))
                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "???"))
                            .add(Keys.ITEM_LORE, Arrays.asList(
                                    Text.of(TextColors.AQUA, "?"),
                                    Text.of(TextColors.GREEN, "?"),
                                    Text.of(TextColors.BLUE, "?"),
                                    Text.of(TextColors.YELLOW, "Goreve baslamak icin onceki gorevi bitir.")))
                            .build());
                }
            } else {
                /* Bilgili Mega Avcisi */
                if(item.equals("mawilite")) {
                    if(visin == 1) {
                        if(gorevdurum.equals("basladi")) {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) PixelmonItemsHeld.mawilite))
                                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "Bilgili Mega Avcisi"))
                                    .add(Keys.ITEM_LORE, Arrays.asList(
                                            Text.of(TextColors.AQUA, "15 boss pokemon oldur."),
                                            Text.of(TextColors.GREEN, "Oduller:"),
                                            Text.of(TextColors.BLUE, "2.000$"),
                                            Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                                    .build());
                        } else {
                            if(gorevdurum.equals("baslamadi")) {
                                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                        .from(ItemStack.of((ItemType) PixelmonItemsHeld.mawilite))
                                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "Bilgili Mega Avcisi"))
                                        .add(Keys.ITEM_LORE, Arrays.asList(
                                                Text.of(TextColors.AQUA, "15 boss pokemon oldur."),
                                                Text.of(TextColors.GREEN, "Oduller:"),
                                                Text.of(TextColors.BLUE, "2.000$"),
                                                Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                        .build());
                            } else {
                                if(gorevdurum.equals("bitirdi")) {
                                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                            .from(ItemStack.of((ItemType) PixelmonItemsHeld.mawilite))
                                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "Bilgili Mega Avcisi"))
                                            .add(Keys.ITEM_LORE, Arrays.asList(
                                                    Text.of(TextColors.AQUA, "15 boss pokemon oldur."),
                                                    Text.of(TextColors.GREEN, "Oduller:"),
                                                    Text.of(TextColors.BLUE, "2.000$"),
                                                    Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                            .build());
                                }
                            }
                        }
                    } else {
                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                .from(ItemStack.of((ItemType) PixelmonItemsHeld.mawilite))
                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "???"))
                                .add(Keys.ITEM_LORE, Arrays.asList(
                                        Text.of(TextColors.AQUA, "?"),
                                        Text.of(TextColors.GREEN, "?"),
                                        Text.of(TextColors.BLUE, "?"),
                                        Text.of(TextColors.YELLOW, "Goreve baslamak icin onceki gorevi bitir.")))
                                .build());
                    }
                } else {
                    /* Usta Mega Avcisi */
                    if(item.equals("mewtwonitey")) {
                        if(visin == 1) {
                            if(gorevdurum.equals("basladi")) {
                                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                        .from(ItemStack.of((ItemType) PixelmonItemsHeld.mewtwonite_y))
                                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Usta Mega Avcisi"))
                                        .add(Keys.ITEM_LORE, Arrays.asList(
                                                Text.of(TextColors.AQUA, "30 boss pokemon oldur."),
                                                Text.of(TextColors.GREEN, "Oduller:"),
                                                Text.of(TextColors.BLUE, "3.000$"),
                                                Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                                        .build());
                            } else {
                                if(gorevdurum.equals("baslamadi")) {
                                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                            .from(ItemStack.of((ItemType) PixelmonItemsHeld.mewtwonite_y))
                                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Usta Mega Avcisi"))
                                            .add(Keys.ITEM_LORE, Arrays.asList(
                                                    Text.of(TextColors.AQUA, "30 boss pokemon oldur."),
                                                    Text.of(TextColors.GREEN, "Oduller:"),
                                                    Text.of(TextColors.BLUE, "3.000$"),
                                                    Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                            .build());
                                } else {
                                    if(gorevdurum.equals("bitirdi")) {
                                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                                .from(ItemStack.of((ItemType) PixelmonItemsHeld.mewtwonite_y))
                                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Usta Mega Avcisi"))
                                                .add(Keys.ITEM_LORE, Arrays.asList(
                                                        Text.of(TextColors.AQUA, "30 boss pokemon oldur."),
                                                        Text.of(TextColors.GREEN, "Oduller:"),
                                                        Text.of(TextColors.BLUE, "3.000$"),
                                                        Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                                .build());
                                    }
                                }
                            }
                        } else {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) PixelmonItemsHeld.mewtwonite_y))
                                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "???"))
                                    .add(Keys.ITEM_LORE, Arrays.asList(
                                            Text.of(TextColors.AQUA, "?"),
                                            Text.of(TextColors.GREEN, "?"),
                                            Text.of(TextColors.BLUE, "?"),
                                            Text.of(TextColors.YELLOW, "Goreve baslamak icin onceki gorevi bitir.")))
                                    .build());
                        }
                    }
                }
            }
        }
    }
    public Inventory getbackpack() {
        return this.inventory;
    }

}
