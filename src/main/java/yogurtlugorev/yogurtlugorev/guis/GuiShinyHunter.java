package yogurtlugorev.yogurtlugorev.guis;

import com.google.common.collect.Iterables;
import com.pixelmonmod.pixelmon.config.PixelmonItems;
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

public class GuiShinyHunter {
    public Inventory inventory;
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./kadayifdb/kadayifquest";
    static final String USER = "sa";
    static final String PASS = "";
    Connection connect = null;
    Statement statmt = null;
    Statement statmt2 = null;

    public GuiShinyHunter(Player player) {
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
                        /* Acemi Shiny Avcisi */
                        if (affected.getType() == PixelmonItems.duskStone) {
                            try {
                                connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                statmt = connect.createStatement();
                                try {
                                    statmt = connect.createStatement();
                                    String sqlara = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                                    ResultSet rsara = statmt.executeQuery(sqlara);
                                    if (rsara.next()) {
                                        if (rsara.getString("shinyavcisi").equals("baslamadi")) {
                                            if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                if(rsara.getString("gorev1").equals("yok")) {
                                                    String sql = "UPDATE Gorev SET shinyavcisi=?, gorev1='shinyavcisi', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                    PreparedStatement ps = connect.prepareStatement(sql);
                                                    ps.setString(1, "g1devam");
                                                    ps.setInt(2, 0);
                                                    ps.executeUpdate();
                                                    Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                    player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Acemi Shiny Avcisi gorevine basladiniz."));
                                                } else {
                                                    if(rsara.getString("gorev2").equals("yok")) {
                                                        String sql = "UPDATE Gorev SET shinyavcisi=?, gorev2='shinyavcisi', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                        PreparedStatement ps = connect.prepareStatement(sql);
                                                        ps.setString(1, "g1devam");
                                                        ps.setInt(2, 0);
                                                        ps.executeUpdate();
                                                        Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                        player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Acemi Shiny Avcisi gorevine basladiniz."));
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
                            /* Tecrübeli Shiny Avcisi */
                            if (affected.getType() == PixelmonItems.thunderStone) {
                                try {
                                    connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                    statmt = connect.createStatement();
                                    try {
                                        statmt = connect.createStatement();
                                        String sqlara = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                                        ResultSet rsara = statmt.executeQuery(sqlara);
                                        if (rsara.next()) {
                                            if (rsara.getString("shinyavcisi").equals("g1bitti")) {
                                                if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                    if(rsara.getString("gorev1").equals("yok")) {
                                                        String sql = "UPDATE Gorev SET shinyavcisi=?, gorev1='shinyavcisi', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                        PreparedStatement ps = connect.prepareStatement(sql);
                                                        ps.setString(1, "g2devam");
                                                        ps.setInt(2, 0);
                                                        ps.executeUpdate();
                                                        Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                        player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Tecrubeli Shiny Avcisi gorevine basladiniz."));
                                                    } else {
                                                        if(rsara.getString("gorev2").equals("yok")) {
                                                            String sql = "UPDATE Gorev SET shinyavcisi=?, gorev2='shinyavcisi', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                            PreparedStatement ps = connect.prepareStatement(sql);
                                                            ps.setString(1, "g2devam");
                                                            ps.setInt(2, 0);
                                                            ps.executeUpdate();
                                                            Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                            player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Tecrubeli Shiny Avcisi gorevine basladiniz."));
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
                                /* Bilgili Shiny Avcisi */
                                if (affected.getType() == PixelmonItems.dawnStone) {
                                    try {
                                        connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                        statmt = connect.createStatement();
                                        try {
                                            statmt = connect.createStatement();
                                            String sqlara = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                                            ResultSet rsara = statmt.executeQuery(sqlara);
                                            if (rsara.next()) {
                                                if (rsara.getString("shinyavcisi").equals("g2bitti")) {
                                                    if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                        if(rsara.getString("gorev1").equals("yok")) {
                                                            String sql = "UPDATE Gorev SET shinyavcisi=?, gorev1='shinyavcisi', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                            PreparedStatement ps = connect.prepareStatement(sql);
                                                            ps.setString(1, "g3devam");
                                                            ps.setInt(2, 0);
                                                            ps.executeUpdate();
                                                            Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                            player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Bilgili Shiny Avcisi gorevine basladiniz."));
                                                        } else {
                                                            if(rsara.getString("gorev2").equals("yok")) {
                                                                String sql = "UPDATE Gorev SET shinyavcisi=?, gorev2='shinyavcisi', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                                PreparedStatement ps = connect.prepareStatement(sql);
                                                                ps.setString(1, "g3devam");
                                                                ps.setInt(2, 0);
                                                                ps.executeUpdate();
                                                                Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                                player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Bilgili Shiny Avcisi gorevine basladiniz."));
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
                                    /* Usta Shiny Avcisi */
                                    if (affected.getType() == PixelmonItems.shinyStone) {
                                        try {
                                            connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                            statmt = connect.createStatement();
                                            try {
                                                statmt = connect.createStatement();
                                                String sqlara = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                                                ResultSet rsara = statmt.executeQuery(sqlara);
                                                if (rsara.next()) {
                                                    if (rsara.getString("shinyavcisi").equals("g3bitti")) {
                                                        if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                            if(rsara.getString("gorev1").equals("yok")) {
                                                                String sql = "UPDATE Gorev SET shinyavcisi=?, gorev1='shinyavcisi', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                                PreparedStatement ps = connect.prepareStatement(sql);
                                                                ps.setString(1, "g4devam");
                                                                ps.setInt(2, 0);
                                                                ps.executeUpdate();
                                                                Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                                player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Usta Shiny Avcisi gorevine basladiniz."));
                                                            } else {
                                                                if(rsara.getString("gorev2").equals("yok")) {
                                                                    String sql = "UPDATE Gorev SET shinyavcisi=?, gorev2='shinyavcisi', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                                    PreparedStatement ps = connect.prepareStatement(sql);
                                                                    ps.setString(1, "g4devam");
                                                                    ps.setInt(2, 0);
                                                                    ps.executeUpdate();
                                                                    Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                                    player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Usta Shiny Avcisi gorevine basladiniz."));
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
                    if(rsara.getString("shinyavcisi").equals("baslamadi")) {
                        setItem(1,0,1,"baslamadi","duskstone");
                        setItem(3,0,0,"baslamadi","thunderstone");
                        setItem(5,0,0,"baslamadi","dawnstone");
                        setItem(7,0,0,"baslamadi","shinystone");
                    } else {
                        // Acemi Shiny Avcisi - devam ediyor
                        if(rsara.getString("shinyavcisi").equals("g1devam")) {
                            setItem(1,0,1,"basladi","duskstone");
                            setItem(3,0,0,"baslamadi","thunderstone");
                            setItem(5,0,0,"baslamadi","dawnstone");
                            setItem(7,0,0,"baslamadi","shinystone");
                        } else {
                            // Acemi Shiny Avcisi bitti
                            if(rsara.getString("shinyavcisi").equals("g1bitti")) {
                                setItem(1,0,1,"bitirdi","duskstone");
                                setItem(3,0,1,"baslamadi","thunderstone");
                                setItem(5,0,0,"baslamadi","dawnstone");
                                setItem(7,0,0,"baslamadi","shinystone");
                            } else {
                                // Tecrübeli Shiny Avcisi devam ediyor
                                if(rsara.getString("shinyavcisi").equals("g2devam")) {
                                    setItem(1,0,1,"bitirdi","duskstone");
                                    setItem(3,0,1,"basladi","thunderstone");
                                    setItem(5,0,0,"baslamadi","dawnstone");
                                    setItem(7,0,0,"baslamadi","shinystone");
                                } else {
                                    // Tecrübeli Shiny Avcisi bitti
                                    if(rsara.getString("shinyavcisi").equals("g2bitti")) {
                                        setItem(1,0,1,"bitirdi","duskstone");
                                        setItem(3,0,1,"bitirdi","thunderstone");
                                        setItem(5,0,1,"baslamadi","dawnstone");
                                        setItem(7,0,0,"baslamadi","shinystone");
                                    } else {
                                        // Bilgili Shiny Avcisi devam ediyor
                                        if(rsara.getString("shinyavcisi").equals("g3devam")) {
                                            setItem(1,0,1,"bitirdi","duskstone");
                                            setItem(3,0,1,"bitirdi","thunderstone");
                                            setItem(5,0,1,"basladi","dawnstone");
                                            setItem(7,0,0,"baslamadi","shinystone");
                                        } else {
                                            // Bilgili Shiny Avcisi bitti
                                            if(rsara.getString("shinyavcisi").equals("g3bitti")) {
                                                setItem(1,0,1,"bitirdi","duskstone");
                                                setItem(3,0,1,"bitirdi","thunderstone");
                                                setItem(5,0,1,"bitirdi","dawnstone");
                                                setItem(7,0,1,"baslamadi","shinystone");
                                            } else {
                                                // Usta Shiny Avcisi devam ediyor
                                                if(rsara.getString("shinyavcisi").equals("g4devam")) {
                                                    setItem(1,0,1,"bitirdi","duskstone");
                                                    setItem(3,0,1,"bitirdi","thunderstone");
                                                    setItem(5,0,1,"bitirdi","dawnstone");
                                                    setItem(7,0,1,"basladi","shinystone");
                                                } else {
                                                    // Usta Shiny Avcisi bitti
                                                    if(rsara.getString("shinyavcisi").equals("g4bitti")) {
                                                        setItem(1,0,1,"bitirdi","duskstone");
                                                        setItem(3,0,1,"bitirdi","thunderstone");
                                                        setItem(5,0,1,"bitirdi","dawnstone");
                                                        setItem(7,0,1,"bitirdi","shinystone");
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
        /* Acemi Shiny Avcisi */
        if(item.equals("duskstone")) {
            if(visin == 1) {
                if(gorevdurum.equals("basladi")) {
                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                            .from(ItemStack.of((ItemType) PixelmonItems.duskStone))
                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Acemi Shiny Avcisi"))
                            .add(Keys.ITEM_LORE, Arrays.asList(
                                    Text.of(TextColors.AQUA, "5 Shiny pokemon yakala."),
                                    Text.of(TextColors.GREEN, "Oduller:"),
                                    Text.of(TextColors.BLUE, "500$"),
                                    Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                            .build());
                } else {
                    if(gorevdurum.equals("baslamadi")) {
                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                .from(ItemStack.of((ItemType) PixelmonItems.duskStone))
                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Acemi Shiny Avcisi"))
                                .add(Keys.ITEM_LORE, Arrays.asList(
                                        Text.of(TextColors.AQUA, "5 Shiny pokemon yakala."),
                                        Text.of(TextColors.GREEN, "Oduller:"),
                                        Text.of(TextColors.BLUE, "500$"),
                                        Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                .build());
                    } else {
                        if(gorevdurum.equals("bitirdi")) {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) PixelmonItems.duskStone))
                                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Acemi Shiny Avcisi"))
                                    .add(Keys.ITEM_LORE, Arrays.asList(
                                            Text.of(TextColors.AQUA, "5 Shiny pokemon yakala."),
                                            Text.of(TextColors.GREEN, "Oduller:"),
                                            Text.of(TextColors.BLUE, "500$"),
                                            Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                    .build());
                        }
                    }
                }
            } else {
                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                        .from(ItemStack.of((ItemType) PixelmonItems.duskStone))
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "???"))
                        .add(Keys.ITEM_LORE, Arrays.asList(
                                Text.of(TextColors.AQUA, "?"),
                                Text.of(TextColors.GREEN, "?"),
                                Text.of(TextColors.BLUE, "?"),
                                Text.of(TextColors.YELLOW, "Goreve baslamak icin onceki gorevi bitir.")))
                        .build());
            }
        } else {
            /* Tecrübeli Shiny Avcisi */
            if(item.equals("thunderstone")) {
                if(visin == 1) {
                    if(gorevdurum.equals("basladi")) {
                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                .from(ItemStack.of((ItemType) PixelmonItems.thunderStone))
                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "Tecrubeli Shiny Avcisi"))
                                .add(Keys.ITEM_LORE, Arrays.asList(
                                        Text.of(TextColors.AQUA, "10 Shiny pokemon yakala."),
                                        Text.of(TextColors.GREEN, "Oduller:"),
                                        Text.of(TextColors.BLUE, "1.000$"),
                                        Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                                .build());
                    } else {
                        if(gorevdurum.equals("baslamadi")) {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) PixelmonItems.thunderStone))
                                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "Tecrubeli Shiny Avcisi"))
                                    .add(Keys.ITEM_LORE, Arrays.asList(
                                            Text.of(TextColors.AQUA, "10 Shiny pokemon yakala."),
                                            Text.of(TextColors.GREEN, "Oduller:"),
                                            Text.of(TextColors.BLUE, "1.000$"),
                                            Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                    .build());
                        } else {
                            if(gorevdurum.equals("bitirdi")) {
                                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                        .from(ItemStack.of((ItemType) PixelmonItems.thunderStone))
                                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "Tecrubeli Shiny Avcisi"))
                                        .add(Keys.ITEM_LORE, Arrays.asList(
                                                Text.of(TextColors.AQUA, "10 Shiny pokemon yakala."),
                                                Text.of(TextColors.GREEN, "Oduller:"),
                                                Text.of(TextColors.BLUE, "1.000$"),
                                                Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                        .build());
                            }
                        }
                    }
                } else {
                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                            .from(ItemStack.of((ItemType) PixelmonItems.thunderStone))
                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "???"))
                            .add(Keys.ITEM_LORE, Arrays.asList(
                                    Text.of(TextColors.AQUA, "?"),
                                    Text.of(TextColors.GREEN, "?"),
                                    Text.of(TextColors.BLUE, "?"),
                                    Text.of(TextColors.YELLOW, "Goreve baslamak icin onceki gorevi bitir.")))
                            .build());
                }
            } else {
                /* Bilgili Shiny Avcisi */
                if(item.equals("dawnstone")) {
                    if(visin == 1) {
                        if(gorevdurum.equals("basladi")) {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) PixelmonItems.dawnStone))
                                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "Bilgili Shiny Avcisi"))
                                    .add(Keys.ITEM_LORE, Arrays.asList(
                                            Text.of(TextColors.AQUA, "25 Shiny pokemon yakala."),
                                            Text.of(TextColors.GREEN, "Oduller:"),
                                            Text.of(TextColors.BLUE, "2.000$"),
                                            Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                                    .build());
                        } else {
                            if(gorevdurum.equals("baslamadi")) {
                                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                        .from(ItemStack.of((ItemType) PixelmonItems.dawnStone))
                                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "Bilgili Shiny Avcisi"))
                                        .add(Keys.ITEM_LORE, Arrays.asList(
                                                Text.of(TextColors.AQUA, "25 Shiny pokemon yakala."),
                                                Text.of(TextColors.GREEN, "Oduller:"),
                                                Text.of(TextColors.BLUE, "2.000$"),
                                                Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                        .build());
                            } else {
                                if(gorevdurum.equals("bitirdi")) {
                                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                            .from(ItemStack.of((ItemType) PixelmonItems.dawnStone))
                                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "Bilgili Shiny Avcisi"))
                                            .add(Keys.ITEM_LORE, Arrays.asList(
                                                    Text.of(TextColors.AQUA, "25 Shiny pokemon yakala."),
                                                    Text.of(TextColors.GREEN, "Oduller:"),
                                                    Text.of(TextColors.BLUE, "2.000$"),
                                                    Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                            .build());
                                }
                            }
                        }
                    } else {
                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                .from(ItemStack.of((ItemType) PixelmonItems.dawnStone))
                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "???"))
                                .add(Keys.ITEM_LORE, Arrays.asList(
                                        Text.of(TextColors.AQUA, "?"),
                                        Text.of(TextColors.GREEN, "?"),
                                        Text.of(TextColors.BLUE, "?"),
                                        Text.of(TextColors.YELLOW, "Goreve baslamak icin onceki gorevi bitir.")))
                                .build());
                    }
                } else {
                    /* Usta Shiny Avcisi */
                    if(item.equals("shinystone")) {
                        if(visin == 1) {
                            if(gorevdurum.equals("basladi")) {
                                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                        .from(ItemStack.of((ItemType) PixelmonItems.shinyStone))
                                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Usta Shiny Avcisi"))
                                        .add(Keys.ITEM_LORE, Arrays.asList(
                                                Text.of(TextColors.AQUA, "75 Shiny pokemon yakala."),
                                                Text.of(TextColors.GREEN, "Oduller:"),
                                                Text.of(TextColors.BLUE, "3.000$"),
                                                Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                                        .build());
                            } else {
                                if(gorevdurum.equals("baslamadi")) {
                                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                            .from(ItemStack.of((ItemType) PixelmonItems.shinyStone))
                                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Usta Shiny Avcisi"))
                                            .add(Keys.ITEM_LORE, Arrays.asList(
                                                    Text.of(TextColors.AQUA, "75 Shiny pokemon yakala."),
                                                    Text.of(TextColors.GREEN, "Oduller:"),
                                                    Text.of(TextColors.BLUE, "3.000$"),
                                                    Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                            .build());
                                } else {
                                    if(gorevdurum.equals("bitirdi")) {
                                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                                .from(ItemStack.of((ItemType) PixelmonItems.shinyStone))
                                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Usta Shiny Avcisi"))
                                                .add(Keys.ITEM_LORE, Arrays.asList(
                                                        Text.of(TextColors.AQUA, "75 Shiny pokemon yakala."),
                                                        Text.of(TextColors.GREEN, "Oduller:"),
                                                        Text.of(TextColors.BLUE, "3.000$"),
                                                        Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                                .build());
                                    }
                                }
                            }
                        } else {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) PixelmonItems.shinyStone))
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