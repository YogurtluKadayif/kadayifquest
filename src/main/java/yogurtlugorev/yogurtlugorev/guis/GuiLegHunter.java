package yogurtlugorev.yogurtlugorev.guis;

import com.google.common.collect.Iterables;
import com.pixelmonmod.pixelmon.config.PixelmonItemsPokeballs;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.item.ItemType;
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

public class GuiLegHunter {
    public Inventory inventory;
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./kadayifdb/kadayifquest";
    static final String USER = "sa";
    static final String PASS = "";
    Connection connect = null;
    Statement statmt = null;
    Statement statmt2 = null;

    public GuiLegHunter(Player player) {
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
                        /* Acemi Leg Avcisi */
                        if (affected.getType() == PixelmonItemsPokeballs.cherishBall) {
                            try {
                                connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                statmt = connect.createStatement();
                                try {
                                    statmt = connect.createStatement();
                                    String sqlara = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                                    ResultSet rsara = statmt.executeQuery(sqlara);
                                    if (rsara.next()) {
                                        if (rsara.getString("legavcisi").equals("baslamadi")) {
                                            if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                if(rsara.getString("gorev1").equals("yok")) {
                                                    String sql = "UPDATE Gorev SET legavcisi=?, gorev1='legavcisi', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                    PreparedStatement ps = connect.prepareStatement(sql);
                                                    ps.setString(1, "g1devam");
                                                    ps.setInt(2, 0);
                                                    ps.executeUpdate();
                                                    Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                    player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Acemi Leg Avcisi gorevine basladiniz."));
                                                } else {
                                                    if(rsara.getString("gorev2").equals("yok")) {
                                                        String sql = "UPDATE Gorev SET legavcisi=?, gorev2='legavcisi', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                        PreparedStatement ps = connect.prepareStatement(sql);
                                                        ps.setString(1, "g1devam");
                                                        ps.setInt(2, 0);
                                                        ps.executeUpdate();
                                                        Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                        player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Acemi Leg Avcisi gorevine basladiniz."));
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
                            /* Tecrübeli Leg Avcisi */
                            if (affected.getType() == PixelmonItemsPokeballs.premierBall) {
                                try {
                                    connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                    statmt = connect.createStatement();
                                    try {
                                        statmt = connect.createStatement();
                                        String sqlara = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                                        ResultSet rsara = statmt.executeQuery(sqlara);
                                        if (rsara.next()) {
                                            if (rsara.getString("legavcisi").equals("g1bitti")) {
                                                if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                    if(rsara.getString("gorev1").equals("yok")) {
                                                        String sql = "UPDATE Gorev SET legavcisi=?, gorev1='legavcisi', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                        PreparedStatement ps = connect.prepareStatement(sql);
                                                        ps.setString(1, "g2devam");
                                                        ps.setInt(2, 0);
                                                        ps.executeUpdate();
                                                        Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                        player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Tecrubeli Leg Avcisi gorevine basladiniz."));
                                                    } else {
                                                        if(rsara.getString("gorev2").equals("yok")) {
                                                            String sql = "UPDATE Gorev SET legavcisi=?, gorev2='legavcisi', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                            PreparedStatement ps = connect.prepareStatement(sql);
                                                            ps.setString(1, "g2devam");
                                                            ps.setInt(2, 0);
                                                            ps.executeUpdate();
                                                            Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                            player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Tecrubeli Leg Avcisi gorevine basladiniz."));
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
                                /* Bilgili Leg Avcisi */
                                if (affected.getType() == PixelmonItemsPokeballs.parkBall) {
                                    try {
                                        connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                        statmt = connect.createStatement();
                                        try {
                                            statmt = connect.createStatement();
                                            String sqlara = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                                            ResultSet rsara = statmt.executeQuery(sqlara);
                                            if (rsara.next()) {
                                                if (rsara.getString("legavcisi").equals("g2bitti")) {
                                                    if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                        if(rsara.getString("gorev1").equals("yok")) {
                                                            String sql = "UPDATE Gorev SET legavcisi=?, gorev1='legavcisi', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                            PreparedStatement ps = connect.prepareStatement(sql);
                                                            ps.setString(1, "g3devam");
                                                            ps.setInt(2, 0);
                                                            ps.executeUpdate();
                                                            Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                            player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Bilgili Leg Avcisi gorevine basladiniz."));
                                                        } else {
                                                            if(rsara.getString("gorev2").equals("yok")) {
                                                                String sql = "UPDATE Gorev SET legavcisi=?, gorev2='legavcisi', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                                PreparedStatement ps = connect.prepareStatement(sql);
                                                                ps.setString(1, "g3devam");
                                                                ps.setInt(2, 0);
                                                                ps.executeUpdate();
                                                                Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                                player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Bilgili Leg Avcisi gorevine basladiniz."));
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
                                    /* Usta Leg Avcisi */
                                    if (affected.getType() == PixelmonItemsPokeballs.masterBall) {
                                        try {
                                            connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                            statmt = connect.createStatement();
                                            try {
                                                statmt = connect.createStatement();
                                                String sqlara = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                                                ResultSet rsara = statmt.executeQuery(sqlara);
                                                if (rsara.next()) {
                                                    if (rsara.getString("legavcisi").equals("g3bitti")) {
                                                        if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                            if(rsara.getString("gorev1").equals("yok")) {
                                                                String sql = "UPDATE Gorev SET legavcisi=?, gorev1='legavcisi', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                                PreparedStatement ps = connect.prepareStatement(sql);
                                                                ps.setString(1, "g4devam");
                                                                ps.setInt(2, 0);
                                                                ps.executeUpdate();
                                                                Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                                player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Usta Leg Avcisi gorevine basladiniz."));
                                                            } else {
                                                                if(rsara.getString("gorev2").equals("yok")) {
                                                                    String sql = "UPDATE Gorev SET legavcisi=?, gorev2='legavcisi', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                                    PreparedStatement ps = connect.prepareStatement(sql);
                                                                    ps.setString(1, "g4devam");
                                                                    ps.setInt(2, 0);
                                                                    ps.executeUpdate();
                                                                    Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                                    player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Usta Leg Avcisi gorevine basladiniz."));
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

                if(rsara.next()) {
                    // başlamadı
                    if(rsara.getString("legavcisi").equals("baslamadi")) {
                        setItem(1,0,1,"baslamadi","cherishball");
                        setItem(3,0,0,"baslamadi","premierball");
                        setItem(5,0,0,"baslamadi","parkball");
                        setItem(7,0,0,"baslamadi","masterball");
                    } else {
                        // Acemi Leg Avcisi - devam ediyor
                        if(rsara.getString("legavcisi").equals("g1devam")) {
                            setItem(1,0,1,"basladi","cherishball");
                            setItem(3,0,0,"baslamadi","premierball");
                            setItem(5,0,0,"baslamadi","parkball");
                            setItem(7,0,0,"baslamadi","masterball");
                        } else {
                            // Acemi Leg Avcisi bitti
                            if(rsara.getString("legavcisi").equals("g1bitti")) {
                                setItem(1,0,1,"bitirdi","cherishball");
                                setItem(3,0,1,"baslamadi","premierball");
                                setItem(5,0,0,"baslamadi","parkball");
                                setItem(7,0,0,"baslamadi","masterball");
                            } else {
                                // Tecrübeli Leg Avcisi devam ediyor
                                if(rsara.getString("legavcisi").equals("g2devam")) {
                                    setItem(1,0,1,"bitirdi","cherishball");
                                    setItem(3,0,1,"basladi","premierball");
                                    setItem(5,0,0,"baslamadi","parkball");
                                    setItem(7,0,0,"baslamadi","masterball");
                                } else {
                                    // Tecrübeli Leg Avcisi bitti
                                    if(rsara.getString("legavcisi").equals("g2bitti")) {
                                        setItem(1,0,1,"bitirdi","cherishball");
                                        setItem(3,0,1,"bitirdi","premierball");
                                        setItem(5,0,1,"baslamadi","parkball");
                                        setItem(7,0,0,"baslamadi","masterball");
                                    } else {
                                        // Bilgili Leg Avcisi devam ediyor
                                        if(rsara.getString("legavcisi").equals("g3devam")) {
                                            setItem(1,0,1,"bitirdi","cherishball");
                                            setItem(3,0,1,"bitirdi","premierball");
                                            setItem(5,0,1,"basladi","parkball");
                                            setItem(7,0,0,"baslamadi","masterball");
                                        } else {
                                            // Bilgili Leg Avcisi bitti
                                            if(rsara.getString("legavcisi").equals("g3bitti")) {
                                                setItem(1,0,1,"bitirdi","cherishball");
                                                setItem(3,0,1,"bitirdi","premierball");
                                                setItem(5,0,1,"bitirdi","parkball");
                                                setItem(7,0,1,"baslamadi","masterball");
                                            } else {
                                                // Usta Leg Avcisi devam ediyor
                                                if(rsara.getString("legavcisi").equals("g4devam")) {
                                                    setItem(1,0,1,"bitirdi","cherishball");
                                                    setItem(3,0,1,"bitirdi","premierball");
                                                    setItem(5,0,1,"bitirdi","parkball");
                                                    setItem(7,0,1,"basladi","masterball");
                                                } else {
                                                    // Usta Leg Avcisi bitti
                                                    if(rsara.getString("legavcisi").equals("g4bitti")) {
                                                        setItem(1,0,1,"bitirdi","cherishball");
                                                        setItem(3,0,1,"bitirdi","premierball");
                                                        setItem(5,0,1,"bitirdi","parkball");
                                                        setItem(7,0,1,"bitirdi","masterball");
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
        /* Acemi Leg Avcisi */
        if(item.equals("cherishball")) {
            if(visin == 1) {
                if(gorevdurum.equals("basladi")) {
                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                            .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.cherishBall))
                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Acemi Leg Avcisi"))
                            .add(Keys.ITEM_LORE, Arrays.asList(
                                    Text.of(TextColors.AQUA, "1 legendary pokemon yakala."),
                                    Text.of(TextColors.GREEN, "Oduller:"),
                                    Text.of(TextColors.BLUE, "500$"),
                                    Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                            .build());
                } else {
                    if(gorevdurum.equals("baslamadi")) {
                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.cherishBall))
                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Acemi Leg Avcisi"))
                                .add(Keys.ITEM_LORE, Arrays.asList(
                                        Text.of(TextColors.AQUA, "1 legendary pokemon yakala."),
                                        Text.of(TextColors.GREEN, "Oduller:"),
                                        Text.of(TextColors.BLUE, "500$"),
                                        Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                .build());
                    } else {
                        if(gorevdurum.equals("bitirdi")) {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.cherishBall))
                                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Acemi Leg Avcisi"))
                                    .add(Keys.ITEM_LORE, Arrays.asList(
                                            Text.of(TextColors.AQUA, "1 legendary pokemon yakala."),
                                            Text.of(TextColors.GREEN, "Oduller:"),
                                            Text.of(TextColors.BLUE, "500$"),
                                            Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                    .build());
                        }
                    }
                }
            } else {
                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                        .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.cherishBall))
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "???"))
                        .add(Keys.ITEM_LORE, Arrays.asList(
                                Text.of(TextColors.AQUA, "?"),
                                Text.of(TextColors.GREEN, "?"),
                                Text.of(TextColors.BLUE, "?"),
                                Text.of(TextColors.YELLOW, "Goreve baslamak icin onceki gorevi bitir.")))
                        .build());
            }
        } else {
            /* Tecrübeli Leg Avcisi */
            if(item.equals("premierball")) {
                if(visin == 1) {
                    if(gorevdurum.equals("basladi")) {
                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.premierBall))
                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "Tecrubeli Leg Avcisi"))
                                .add(Keys.ITEM_LORE, Arrays.asList(
                                        Text.of(TextColors.AQUA, "3 legendary pokemon yakala."),
                                        Text.of(TextColors.GREEN, "Oduller:"),
                                        Text.of(TextColors.BLUE, "1.000$"),
                                        Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                                .build());
                    } else {
                        if(gorevdurum.equals("baslamadi")) {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.premierBall))
                                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "Tecrubeli Leg Avcisi"))
                                    .add(Keys.ITEM_LORE, Arrays.asList(
                                            Text.of(TextColors.AQUA, "3 legendary pokemon yakala."),
                                            Text.of(TextColors.GREEN, "Oduller:"),
                                            Text.of(TextColors.BLUE, "1.000$"),
                                            Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                    .build());
                        } else {
                            if(gorevdurum.equals("bitirdi")) {
                                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                        .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.premierBall))
                                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "Tecrubeli Leg Avcisi"))
                                        .add(Keys.ITEM_LORE, Arrays.asList(
                                                Text.of(TextColors.AQUA, "3 legendary pokemon yakala."),
                                                Text.of(TextColors.GREEN, "Oduller:"),
                                                Text.of(TextColors.BLUE, "1.000$"),
                                                Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                        .build());
                            }
                        }
                    }
                } else {
                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                            .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.premierBall))
                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "???"))
                            .add(Keys.ITEM_LORE, Arrays.asList(
                                    Text.of(TextColors.AQUA, "?"),
                                    Text.of(TextColors.GREEN, "?"),
                                    Text.of(TextColors.BLUE, "?"),
                                    Text.of(TextColors.YELLOW, "Goreve baslamak icin onceki gorevi bitir.")))
                            .build());
                }
            } else {
                /* Bilgili Leg Avcisi */
                if(item.equals("parkball")) {
                    if(visin == 1) {
                        if(gorevdurum.equals("basladi")) {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.parkBall))
                                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "Bilgili Leg Avcisi"))
                                    .add(Keys.ITEM_LORE, Arrays.asList(
                                            Text.of(TextColors.AQUA, "5 legendary pokemon yakala."),
                                            Text.of(TextColors.GREEN, "Oduller:"),
                                            Text.of(TextColors.BLUE, "2.000$"),
                                            Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                                    .build());
                        } else {
                            if(gorevdurum.equals("baslamadi")) {
                                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                        .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.parkBall))
                                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "Bilgili Leg Avcisi"))
                                        .add(Keys.ITEM_LORE, Arrays.asList(
                                                Text.of(TextColors.AQUA, "5 legendary pokemon yakala."),
                                                Text.of(TextColors.GREEN, "Oduller:"),
                                                Text.of(TextColors.BLUE, "2.000$"),
                                                Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                        .build());
                            } else {
                                if(gorevdurum.equals("bitirdi")) {
                                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                            .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.parkBall))
                                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "Bilgili Leg Avcisi"))
                                            .add(Keys.ITEM_LORE, Arrays.asList(
                                                    Text.of(TextColors.AQUA, "5 legendary pokemon yakala."),
                                                    Text.of(TextColors.GREEN, "Oduller:"),
                                                    Text.of(TextColors.BLUE, "2.000$"),
                                                    Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                            .build());
                                }
                            }
                        }
                    } else {
                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.parkBall))
                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "???"))
                                .add(Keys.ITEM_LORE, Arrays.asList(
                                        Text.of(TextColors.AQUA, "?"),
                                        Text.of(TextColors.GREEN, "?"),
                                        Text.of(TextColors.BLUE, "?"),
                                        Text.of(TextColors.YELLOW, "Goreve baslamak icin onceki gorevi bitir.")))
                                .build());
                    }
                } else {
                    /* Usta Leg Avcisi */
                    if(item.equals("masterball")) {
                        if(visin == 1) {
                            if(gorevdurum.equals("basladi")) {
                                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                        .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.masterBall))
                                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Usta Leg Avcisi"))
                                        .add(Keys.ITEM_LORE, Arrays.asList(
                                                Text.of(TextColors.AQUA, "10 legendary pokemon yakala."),
                                                Text.of(TextColors.GREEN, "Oduller:"),
                                                Text.of(TextColors.BLUE, "3.000$"),
                                                Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                                        .build());
                            } else {
                                if(gorevdurum.equals("baslamadi")) {
                                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                            .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.masterBall))
                                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Usta Leg Avcisi"))
                                            .add(Keys.ITEM_LORE, Arrays.asList(
                                                    Text.of(TextColors.AQUA, "10 legendary pokemon yakala."),
                                                    Text.of(TextColors.GREEN, "Oduller:"),
                                                    Text.of(TextColors.BLUE, "3.000$"),
                                                    Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                            .build());
                                } else {
                                    if(gorevdurum.equals("bitirdi")) {
                                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                                .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.masterBall))
                                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Usta Leg Avcisi"))
                                                .add(Keys.ITEM_LORE, Arrays.asList(
                                                        Text.of(TextColors.AQUA, "10 legendary pokemon yakala."),
                                                        Text.of(TextColors.GREEN, "Oduller:"),
                                                        Text.of(TextColors.BLUE, "3.000$"),
                                                        Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                                .build());
                                    }
                                }
                            }
                        } else {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.masterBall))
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
