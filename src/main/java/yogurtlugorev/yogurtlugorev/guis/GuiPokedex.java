package yogurtlugorev.yogurtlugorev.guis;

import com.google.common.collect.Iterables;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.config.PixelmonItemsPokeballs;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.api.Sponge;
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
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import yogurtlugorev.yogurtlugorev.Yogurtlugorev;

import java.sql.*;
import java.util.Arrays;

public class GuiPokedex {
    public Inventory inventory;
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./kadayifdb/kadayifquest";
    static final String USER = "sa";
    static final String PASS = "";
    Connection connect = null;
    Statement statmt = null;
    Statement statmt2 = null;

    public GuiPokedex(Player player) {
        this.inventory = Inventory.builder()
                .of(InventoryArchetypes.DOUBLE_CHEST)
                .property(InventoryTitle.PROPERTY_NAME, InventoryTitle.of(Text.of(TextColors.AQUA, "KadayifQuest")))
                .property("inventorydimension", InventoryDimension.of(9, 1))
                .listener(ClickInventoryEvent.class, (ClickInventoryEvent event) -> {
                    event.setCancelled(true);
                    ItemStack affected;
                    for(SlotTransaction slotTransaction : event.getTransactions()) {
                        Slot slot = slotTransaction.getSlot();
                        Container container = event.getTargetInventory();
                        SlotIndex pos = Iterables.getOnlyElement(slot.parent().getProperties(slot, SlotIndex.class));
                        affected = event.getTransactions().get(0).getOriginal().createStack();

                        /* Acemi Avcı */
                        if(affected.getType() == PixelmonItemsPokeballs.pokeBall) {
                            try {
                                connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                statmt = connect.createStatement();
                                try {
                                    statmt = connect.createStatement();
                                    String sqlara = "SELECT * FROM Gorev WHERE oyuncu='"+ player.getName() + "'";
                                    ResultSet rsara = statmt.executeQuery(sqlara);
                                    if(rsara.next()) {
                                        if (rsara.getString("pokedex").equals("baslamadi")) {
                                            if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                if(rsara.getString("gorev1").equals("yok")) {
                                                    String sql = "UPDATE Gorev SET pokedex=?, gorev1='pokedex', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                    PreparedStatement ps = connect.prepareStatement(sql);
                                                    ps.setString(1, "g1devam");
                                                    ps.setInt(2, 0);
                                                    ps.executeUpdate();
                                                    player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Acemi Koleksiyoncu gorevine basladiniz."));
                                                } else {
                                                    if(rsara.getString("gorev2").equals("yok")) {
                                                        String sql = "UPDATE Gorev SET pokedex=?, gorev2='pokedex', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                        PreparedStatement ps = connect.prepareStatement(sql);
                                                        ps.setString(1, "g1devam");
                                                        ps.setInt(2, 0);
                                                        ps.executeUpdate();
                                                        player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Acemi Koleksiyoncu gorevine basladiniz."));
                                                    }
                                                }
                                            } else {
                                                player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "2 gorevden fazlasini alamazsiniz."));
                                            }
                                        } else {
                                            if (rsara.getString("pokedex").equals("g1devam")) {
                                                pokedexKontrol(player);
                                            } else {
                                                player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Bu gorevi zaten bitirdiniz."));
                                            }
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
                            /* Tecrübeli Avcı */
                            if(affected.getType() == PixelmonItemsPokeballs.greatBall) {
                                try {
                                    connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                    statmt = connect.createStatement();
                                    try {
                                        statmt = connect.createStatement();
                                        String sqlara = "SELECT * FROM Gorev WHERE oyuncu='"+ player.getName() + "'";
                                        ResultSet rsara = statmt.executeQuery(sqlara);
                                        if(rsara.next()) {
                                            if (rsara.getString("pokedex").equals("g1bitti")) {
                                                if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                    if(rsara.getString("gorev1").equals("yok")) {
                                                        String sql = "UPDATE Gorev SET pokedex=?, gorev1='pokedex', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                        PreparedStatement ps = connect.prepareStatement(sql);
                                                        ps.setString(1, "g2devam");
                                                        ps.setInt(2, 0);
                                                        ps.executeUpdate();
                                                        player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Tecrubeli Koleksiyoncu gorevine basladiniz."));
                                                    } else {
                                                        if(rsara.getString("gorev2").equals("yok")) {
                                                            String sql = "UPDATE Gorev SET pokedex=?, gorev2='pokedex', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                            PreparedStatement ps = connect.prepareStatement(sql);
                                                            ps.setString(1, "g2devam");
                                                            ps.setInt(2, 0);
                                                            ps.executeUpdate();
                                                            player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Tecrubeli Koleksiyoncu gorevine basladiniz."));
                                                        }
                                                    }
                                                } else {
                                                    player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "2 gorevden fazlasini alamazsiniz."));
                                                }
                                            } else {
                                                if (rsara.getString("pokedex").equals("g2devam")) {
                                                    pokedexKontrol(player);
                                                } else {
                                                    player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Onceki gorevdesiniz veya zaten bitirdiniz."));
                                                }
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
                                /* Bilgili Avcı */
                                if(affected.getType() == PixelmonItemsPokeballs.ultraBall) {
                                    try {
                                        connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                        statmt = connect.createStatement();
                                        try {
                                            statmt = connect.createStatement();
                                            String sqlara = "SELECT * FROM Gorev WHERE oyuncu='"+ player.getName() + "'";
                                            ResultSet rsara = statmt.executeQuery(sqlara);
                                            if(rsara.next()) {
                                                if (rsara.getString("pokedex").equals("g2bitti")) {
                                                    if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                        if(rsara.getString("gorev1").equals("yok")) {
                                                            String sql = "UPDATE Gorev SET pokedex=?, gorev1='pokedex', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                            PreparedStatement ps = connect.prepareStatement(sql);
                                                            ps.setString(1, "g3devam");
                                                            ps.setInt(2, 0);
                                                            ps.executeUpdate();
                                                            player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Bilgili Koleksiyoncu gorevine basladiniz."));
                                                        } else {
                                                            if(rsara.getString("gorev2").equals("yok")) {
                                                                String sql = "UPDATE Gorev SET pokedex=?, gorev2='pokedex', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                                PreparedStatement ps = connect.prepareStatement(sql);
                                                                ps.setString(1, "g3devam");
                                                                ps.setInt(2, 0);
                                                                ps.executeUpdate();
                                                                player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Bilgili Koleksiyoncu gorevine basladiniz."));
                                                            }
                                                        }
                                                    } else {
                                                        player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "2 gorevden fazlasini alamazsiniz."));
                                                    }
                                                } else {
                                                    if (rsara.getString("pokedex").equals("g3devam")) {
                                                        pokedexKontrol(player);
                                                    } else {
                                                        player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Onceki gorevdesiniz veya zaten bitirdiniz."));
                                                    }
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
                                    /* Usta Avcı */
                                    if(affected.getType() == PixelmonItemsPokeballs.masterBall) {
                                        try {
                                            connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                            statmt = connect.createStatement();
                                            try {
                                                statmt = connect.createStatement();
                                                String sqlara = "SELECT * FROM Gorev WHERE oyuncu='"+ player.getName() + "'";
                                                ResultSet rsara = statmt.executeQuery(sqlara);
                                                if(rsara.next()) {
                                                    if (rsara.getString("pokedex").equals("g3bitti")) {
                                                        if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                            if(rsara.getString("gorev1").equals("yok")) {
                                                                String sql = "UPDATE Gorev SET pokedex=?, gorev1='pokedex', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                                PreparedStatement ps = connect.prepareStatement(sql);
                                                                ps.setString(1, "g4devam");
                                                                ps.setInt(2, 0);
                                                                ps.executeUpdate();
                                                                player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Usta Koleksiyoncu gorevine basladiniz."));
                                                            } else {
                                                                if(rsara.getString("gorev2").equals("yok")) {
                                                                    String sql = "UPDATE Gorev SET pokedex=?, gorev2='pokedex', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                                    PreparedStatement ps = connect.prepareStatement(sql);
                                                                    ps.setString(1, "g4devam");
                                                                    ps.setInt(2, 0);
                                                                    ps.executeUpdate();
                                                                    player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Usta Koleksiyoncu gorevine basladiniz."));
                                                                }
                                                            }
                                                        } else {
                                                            player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "2 gorevden fazlasini alamazsiniz."));
                                                        }
                                                    } else {
                                                        if (rsara.getString("pokedex").equals("g4devam")) {
                                                            pokedexKontrol(player);
                                                        } else {
                                                            player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Onceki gorevdesiniz veya zaten bitirdiniz."));
                                                        }                                                    }
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
                    if(rsara.getString("pokedex").equals("baslamadi")) {
                        setBall(1,0,1,"baslamadi","pokeball");
                        setBall(3,0,0,"baslamadi","greatball");
                        setBall(5,0,0,"baslamadi","ultraball");
                        setBall(7,0,0,"baslamadi","masterball");
                    } else {
                        // Acemi Avcı - devam ediyor
                        if(rsara.getString("pokedex").equals("g1devam")) {
                            setBall(1,0,1,"basladi","pokeball");
                            setBall(3,0,0,"baslamadi","greatball");
                            setBall(5,0,0,"baslamadi","ultraball");
                            setBall(7,0,0,"baslamadi","masterball");
                        } else {
                            // Acemi Avcı bitti
                            if(rsara.getString("pokedex").equals("g1bitti")) {
                                setBall(1,0,1,"bitirdi","pokeball");
                                setBall(3,0,1,"baslamadi","greatball");
                                setBall(5,0,0,"baslamadi","ultraball");
                                setBall(7,0,0,"baslamadi","masterball");
                            } else {
                                // Tecrübeli Avcı devam ediyor
                                if(rsara.getString("pokedex").equals("g2devam")) {
                                    setBall(1,0,1,"bitirdi","pokeball");
                                    setBall(3,0,1,"basladi","greatball");
                                    setBall(5,0,0,"baslamadi","ultraball");
                                    setBall(7,0,0,"baslamadi","masterball");
                                } else {
                                    // Tecrübeli Avcı bitti
                                    if(rsara.getString("pokedex").equals("g2bitti")) {
                                        setBall(1,0,1,"bitirdi","pokeball");
                                        setBall(3,0,1,"bitirdi","greatball");
                                        setBall(5,0,1,"baslamadi","ultraball");
                                        setBall(7,0,0,"baslamadi","masterball");
                                    } else {
                                        // Bilgili Avcı devam ediyor
                                        if(rsara.getString("pokedex").equals("g3devam")) {
                                            setBall(1,0,1,"bitirdi","pokeball");
                                            setBall(3,0,1,"bitirdi","greatball");
                                            setBall(5,0,1,"basladi","ultraball");
                                            setBall(7,0,0,"baslamadi","masterball");
                                        } else {
                                            // Bilgili Avcı bitti
                                            if(rsara.getString("pokedex").equals("g3bitti")) {
                                                setBall(1,0,1,"bitirdi","pokeball");
                                                setBall(3,0,1,"bitirdi","greatball");
                                                setBall(5,0,1,"bitirdi","ultraball");
                                                setBall(7,0,1,"baslamadi","masterball");
                                            } else {
                                                // Usta Avcı devam ediyor
                                                if(rsara.getString("pokedex").equals("g4devam")) {
                                                    setBall(1,0,1,"bitirdi","pokeball");
                                                    setBall(3,0,1,"bitirdi","greatball");
                                                    setBall(5,0,1,"bitirdi","ultraball");
                                                    setBall(7,0,1,"basladi","masterball");
                                                } else {
                                                    // Usta Avcı bitti
                                                    if(rsara.getString("pokedex").equals("g4bitti")) {
                                                        setBall(1,0,1,"bitirdi","pokeball");
                                                        setBall(3,0,1,"bitirdi","greatball");
                                                        setBall(5,0,1,"bitirdi","ultraball");
                                                        setBall(7,0,1,"bitirdi","masterball");
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

    public void setBall(Integer x, Integer y, Integer visin, String gorevdurum, String ball) {
        // x,y koordinat; visin görünür görünmez(??? || isim); ilerleme ilerleme; gorevdurum basladi baslamadi bitirdi; ball ball;
        /* Pokeball - Acemi Avcı */
        if(ball.equals("pokeball")) {
            if(visin == 1) {
                if(gorevdurum.equals("basladi")) {
                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                            .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.pokeBall))
                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Acemi Koleksiyoncu"))
                            .add(Keys.ITEM_LORE, Arrays.asList(
                                    Text.of(TextColors.AQUA, "100 pokedex girdisine sahip ol."),
                                    Text.of(TextColors.GREEN, "Oduller:"),
                                    Text.of(TextColors.BLUE, "500$"),
                                    Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                            .build());
                } else {
                    if(gorevdurum.equals("baslamadi")) {
                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.pokeBall))
                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Acemi Koleksiyoncu"))
                                .add(Keys.ITEM_LORE, Arrays.asList(
                                        Text.of(TextColors.AQUA, "100 pokedex girdisine sahip ol."),
                                        Text.of(TextColors.GREEN, "Oduller:"),
                                        Text.of(TextColors.BLUE, "500$"),
                                        Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                .build());
                    } else {
                        if(gorevdurum.equals("bitirdi")) {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.pokeBall))
                                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Acemi Koleksiyoncu"))
                                    .add(Keys.ITEM_LORE, Arrays.asList(
                                            Text.of(TextColors.AQUA, "100 pokedex girdisine sahip ol."),
                                            Text.of(TextColors.GREEN, "Oduller:"),
                                            Text.of(TextColors.BLUE, "500$"),
                                            Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                    .build());
                        }
                    }
                }
            } else {
                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                        .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.pokeBall))
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "???"))
                        .add(Keys.ITEM_LORE, Arrays.asList(
                                Text.of(TextColors.AQUA, "?"),
                                Text.of(TextColors.GREEN, "?"),
                                Text.of(TextColors.BLUE, "?"),
                                Text.of(TextColors.YELLOW, "Goreve baslamak icin onceki gorevi bitir.")))
                        .build());
            }
        } else {
            /* Great Ball - Tecrübeli Avcı */
            if(ball.equals("greatball")) {
                if(visin == 1) {
                    if(gorevdurum.equals("basladi")) {
                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.greatBall))
                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "Tecrubeli Koleksiyoncu"))
                                .add(Keys.ITEM_LORE, Arrays.asList(
                                        Text.of(TextColors.AQUA, "200 pokedex girdisine sahip ol."),
                                        Text.of(TextColors.GREEN, "Oduller:"),
                                        Text.of(TextColors.BLUE, "1.000$"),
                                        Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                                .build());
                    } else {
                        if(gorevdurum.equals("baslamadi")) {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.greatBall))
                                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "Tecrubeli Koleksiyoncu"))
                                    .add(Keys.ITEM_LORE, Arrays.asList(
                                            Text.of(TextColors.AQUA, "200 pokedex girdisine sahip ol."),
                                            Text.of(TextColors.GREEN, "Oduller:"),
                                            Text.of(TextColors.BLUE, "1.000$"),
                                            Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                    .build());
                        } else {
                            if(gorevdurum.equals("bitirdi")) {
                                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                        .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.greatBall))
                                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "Tecrubeli Koleksiyoncu"))
                                        .add(Keys.ITEM_LORE, Arrays.asList(
                                                Text.of(TextColors.AQUA, "200 pokedex girdisine sahip ol."),
                                                Text.of(TextColors.GREEN, "Oduller:"),
                                                Text.of(TextColors.BLUE, "1.000$"),
                                                Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                        .build());
                            }
                        }
                    }
                } else {
                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                            .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.greatBall))
                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "???"))
                            .add(Keys.ITEM_LORE, Arrays.asList(
                                    Text.of(TextColors.AQUA, "?"),
                                    Text.of(TextColors.GREEN, "?"),
                                    Text.of(TextColors.BLUE, "?"),
                                    Text.of(TextColors.YELLOW, "Goreve baslamak icin onceki gorevi bitir.")))
                            .build());
                }
            } else {
                /* Ultra Ball - Bilgili Avcı */
                if(ball.equals("ultraball")) {
                    if(visin == 1) {
                        if(gorevdurum.equals("basladi")) {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.ultraBall))
                                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "Bilgili Koleksiyoncu"))
                                    .add(Keys.ITEM_LORE, Arrays.asList(
                                            Text.of(TextColors.AQUA, "400 pokedex girdisine sahip ol."),
                                            Text.of(TextColors.GREEN, "Oduller:"),
                                            Text.of(TextColors.BLUE, "2.000$"),
                                            Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                                    .build());
                        } else {
                            if(gorevdurum.equals("baslamadi")) {
                                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                        .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.ultraBall))
                                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "Bilgili Koleksiyoncu"))
                                        .add(Keys.ITEM_LORE, Arrays.asList(
                                                Text.of(TextColors.AQUA, "400 pokedex girdisine sahip ol."),
                                                Text.of(TextColors.GREEN, "Oduller:"),
                                                Text.of(TextColors.BLUE, "2.000$"),
                                                Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                        .build());
                            } else {
                                if(gorevdurum.equals("bitirdi")) {
                                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                            .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.ultraBall))
                                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "Bilgili Koleksiyoncu"))
                                            .add(Keys.ITEM_LORE, Arrays.asList(
                                                    Text.of(TextColors.AQUA, "400 pokedex girdisine sahip ol."),
                                                    Text.of(TextColors.GREEN, "Oduller:"),
                                                    Text.of(TextColors.BLUE, "2.000$"),
                                                    Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                            .build());
                                }
                            }
                        }
                    } else {
                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.ultraBall))
                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "???"))
                                .add(Keys.ITEM_LORE, Arrays.asList(
                                        Text.of(TextColors.AQUA, "?"),
                                        Text.of(TextColors.GREEN, "?"),
                                        Text.of(TextColors.BLUE, "?"),
                                        Text.of(TextColors.YELLOW, "Goreve baslamak icin onceki gorevi bitir.")))
                                .build());
                    }
                } else {
                    /* Master Ball - Usta Avcı */
                    if(ball.equals("masterball")) {
                        if(visin == 1) {
                            if(gorevdurum.equals("basladi")) {
                                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                        .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.masterBall))
                                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Usta Koleksiyoncu"))
                                        .add(Keys.ITEM_LORE, Arrays.asList(
                                                Text.of(TextColors.AQUA, "800 pokedex girdisine sahip ol."),
                                                Text.of(TextColors.GREEN, "Oduller:"),
                                                Text.of(TextColors.BLUE, "3.000$"),
                                                Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                                        .build());
                            } else {
                                if(gorevdurum.equals("baslamadi")) {
                                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                            .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.masterBall))
                                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Usta Koleksiyoncu"))
                                            .add(Keys.ITEM_LORE, Arrays.asList(
                                                    Text.of(TextColors.AQUA, "800 pokedex girdisine sahip ol."),
                                                    Text.of(TextColors.GREEN, "Oduller:"),
                                                    Text.of(TextColors.BLUE, "3.000$"),
                                                    Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                            .build());
                                } else {
                                    if(gorevdurum.equals("bitirdi")) {
                                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                                .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.masterBall))
                                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Usta Koleksiyoncu"))
                                                .add(Keys.ITEM_LORE, Arrays.asList(
                                                        Text.of(TextColors.AQUA, "800 pokedex girdisine sahip ol."),
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

    public void pokedexKontrol(Player p) {
        if (p.isOnline()) {
            PlayerPartyStorage party = Pixelmon.storageManager.getParty((EntityPlayerMP) p);
            int pokent = party.pokedex.countCaught();
            if (pokent >= 100) {
                try {
                    connect = DriverManager.getConnection(DB_URL, USER, PASS);
                    statmt = connect.createStatement();
                    try {
                        statmt = connect.createStatement();
                        String sqlrs = "SELECT * FROM Gorev WHERE oyuncu='" + p.getName() + "'";
                        ResultSet rs = statmt.executeQuery(sqlrs);
                        if (rs.next()) {
                            if (rs.getString("pokedex").equals("g1devam") && pokent >= 100) {
                                if (rs.getString("gorev1").equals("pokedex")) {
                                    statmt = connect.createStatement();
                                    String sql2 = "UPDATE Gorev SET  pokedex=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                    ps2.setString(1, "g1bitti");
                                    ps2.executeUpdate();
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 1000");
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Acemi Koleksiyoncu gorevini bitirdiniz, tebrikler."));
                                } else {
                                    if (rs.getString("gorev2").equals("pokedex")) {
                                        statmt = connect.createStatement();
                                        String sql2 = "UPDATE Gorev SET  pokedex=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                        ps2.setString(1, "g1bitti");
                                        ps2.executeUpdate();
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 1000");
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Acemi Koleksiyoncu gorevini bitirdiniz, tebrikler."));
                                    }
                                }
                            } else {
                                if (rs.getString("pokedex").equals("g2devam") && pokent >= 200) {
                                    if (rs.getString("gorev1").equals("pokedex")) {
                                        statmt = connect.createStatement();
                                        String sql2 = "UPDATE Gorev SET  pokedex=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                        ps2.setString(1, "g2bitti");
                                        ps2.executeUpdate();
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 5000");
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Tecrubeli Koleksiyoncu gorevini bitirdiniz, tebrikler."));
                                    } else {
                                        if (rs.getString("gorev2").equals("pokedex")) {
                                            statmt = connect.createStatement();
                                            String sql2 = "UPDATE Gorev SET  pokedex=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "g2bitti");
                                            ps2.executeUpdate();
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 5000");
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Tecrubeli Koleksiyoncu gorevini bitirdiniz, tebrikler."));
                                        }
                                    }
                                } else {
                                    if (rs.getString("pokedex").equals("g3devam") && pokent >= 400) {
                                        if (rs.getString("gorev1").equals("pokedex")) {
                                            statmt = connect.createStatement();
                                            String sql2 = "UPDATE Gorev SET  pokedex=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "g3bitti");
                                            ps2.executeUpdate();
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 15000");
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bilgili Koleksiyoncu gorevini bitirdiniz, tebrikler."));
                                        } else {
                                            if (rs.getString("gorev2").equals("pokedex")) {
                                                statmt = connect.createStatement();
                                                String sql2 = "UPDATE Gorev SET  pokedex=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                ps2.setString(1, "g3bitti");
                                                ps2.executeUpdate();
                                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 15000");
                                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bilgili Koleksiyoncu gorevini bitirdiniz, tebrikler."));
                                            }
                                        }
                                    } else {
                                        if (rs.getString("pokedex").equals("g4devam") && pokent >= 800) {
                                            if (rs.getString("gorev1").equals("pokedex")) {
                                                statmt = connect.createStatement();
                                                String sql2 = "UPDATE Gorev SET  pokedex=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                ps2.setString(1, "g4bitti");
                                                ps2.executeUpdate();
                                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 50000");
                                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Usta Koleksiyoncu gorevini bitirdiniz, tebrikler."));
                                                Text ytext = Text.builder("[").color(TextColors.AQUA).append(
                                                        Text.builder("kadayifQuest").color(TextColors.LIGHT_PURPLE).build()).append(
                                                        Text.builder("] ").color(TextColors.AQUA).build()).append(
                                                        Text.builder(p.getName()).color(TextColors.BLUE).build()).append(
                                                        Text.builder(" isimli oyuncu Pokemon Koleksiyoncusu gorevini tamamladi. -> ").color(TextColors.GREEN).build()).append(
                                                        Text.builder("/quest").color(TextColors.BLUE).onHover(TextActions.showText(Text.of(TextColors.LIGHT_PURPLE, "Komutunu kullanarak gorevlere erisebilirsiniz."))).build()).append().append().build();
                                                Sponge.getServer().getBroadcastChannel().send(ytext);
                                            } else {
                                                if (rs.getString("gorev2").equals("pokedex")) {
                                                    statmt = connect.createStatement();
                                                    String sql2 = "UPDATE Gorev SET  pokedex=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                    ps2.setString(1, "g4bitti");
                                                    ps2.executeUpdate();
                                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 50000");
                                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Usta Koleksiyoncu gorevini bitirdiniz, tebrikler."));
                                                    Text ytext = Text.builder("[").color(TextColors.AQUA).append(
                                                            Text.builder("kadayifQuest").color(TextColors.LIGHT_PURPLE).build()).append(
                                                            Text.builder("] ").color(TextColors.AQUA).build()).append(
                                                            Text.builder(p.getName()).color(TextColors.BLUE).build()).append(
                                                            Text.builder(" isimli oyuncu Pokemon Koleksiyoncusu gorevini tamamladi. -> ").color(TextColors.GREEN).build()).append(
                                                            Text.builder("/quest").color(TextColors.BLUE).onHover(TextActions.showText(Text.of(TextColors.LIGHT_PURPLE, "Komutunu kullanarak gorevlere erisebilirsiniz."))).build()).append().append().build();
                                                    Sponge.getServer().getBroadcastChannel().send(ytext);
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
                            if (statmt2 != null) statmt2.close();
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

    public Inventory getbackpack() {
        return this.inventory;
    }
}
