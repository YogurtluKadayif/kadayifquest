package yogurtlugorev.yogurtlugorev.guis;

import com.google.common.collect.Iterables;
import net.minecraft.init.Blocks;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.filter.cause.First;
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
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import yogurtlugorev.yogurtlugorev.Yogurtlugorev;

import java.sql.*;
import java.util.Arrays;
import java.util.UUID;

public class GuiDogasever {
    public Inventory inventory;
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./kadayifdb/kadayifquest";
    static final String USER = "sa";
    static final String PASS = "";
    Connection connect = null;
    Statement statmt = null;
    Statement statmt2 = null;

    public GuiDogasever(Player player) {
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
                        /* Acemi Dogasever */
                        if (affected.getType() == ItemTypes.DEADBUSH) {
                            try {
                                connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                statmt = connect.createStatement();
                                try {
                                    statmt = connect.createStatement();
                                    String sqlara = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                                    ResultSet rsara = statmt.executeQuery(sqlara);
                                    if (rsara.next()) {
                                        if (rsara.getString("dogasever").equals("baslamadi")) {
                                            if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                if(rsara.getString("gorev1").equals("yok")) {
                                                    String sql = "UPDATE Gorev SET dogasever=?, gorev1='dogasever', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                    PreparedStatement ps = connect.prepareStatement(sql);
                                                    ps.setString(1, "g1devam");
                                                    ps.setInt(2, 0);
                                                    ps.executeUpdate();
                                                    Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                    player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Acemi Dogasever gorevine basladiniz."));
                                                } else {
                                                    if(rsara.getString("gorev2").equals("yok")) {
                                                        String sql = "UPDATE Gorev SET dogasever=?, gorev2='dogasever', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                        PreparedStatement ps = connect.prepareStatement(sql);
                                                        ps.setString(1, "g1devam");
                                                        ps.setInt(2, 0);
                                                        ps.executeUpdate();
                                                        Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                        player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Acemi Dogasever gorevine basladiniz."));
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
                            /* Tecrübeli Dogasever */
                            if (affected.getType() == ItemTypes.CACTUS) {
                                try {
                                    connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                    statmt = connect.createStatement();
                                    try {
                                        statmt = connect.createStatement();
                                        String sqlara = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                                        ResultSet rsara = statmt.executeQuery(sqlara);
                                        if (rsara.next()) {
                                            if (rsara.getString("dogasever").equals("g1bitti")) {
                                                if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                    if(rsara.getString("gorev1").equals("yok")) {
                                                        String sql = "UPDATE Gorev SET dogasever=?, gorev1='dogasever', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                        PreparedStatement ps = connect.prepareStatement(sql);
                                                        ps.setString(1, "g2devam");
                                                        ps.setInt(2, 0);
                                                        ps.executeUpdate();
                                                        Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                        player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Tecrubeli Dogasever gorevine basladiniz."));
                                                    } else {
                                                        if(rsara.getString("gorev2").equals("yok")) {
                                                            String sql = "UPDATE Gorev SET dogasever=?, gorev2='dogasever', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                            PreparedStatement ps = connect.prepareStatement(sql);
                                                            ps.setString(1, "g2devam");
                                                            ps.setInt(2, 0);
                                                            ps.executeUpdate();
                                                            Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                            player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Tecrubeli Dogasever gorevine basladiniz."));
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
                                /* Bilgili Dogasever */
                                if (affected.getType() == ItemTypes.RED_FLOWER) {
                                    try {
                                        connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                        statmt = connect.createStatement();
                                        try {
                                            statmt = connect.createStatement();
                                            String sqlara = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                                            ResultSet rsara = statmt.executeQuery(sqlara);
                                            if (rsara.next()) {
                                                if (rsara.getString("dogasever").equals("g2bitti")) {
                                                    if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                        if(rsara.getString("gorev1").equals("yok")) {
                                                            String sql = "UPDATE Gorev SET dogasever=?, gorev1='dogasever', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                            PreparedStatement ps = connect.prepareStatement(sql);
                                                            ps.setString(1, "g3devam");
                                                            ps.setInt(2, 0);
                                                            ps.executeUpdate();
                                                            Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                            player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Bilgili Dogasever gorevine basladiniz."));
                                                        } else {
                                                            if(rsara.getString("gorev2").equals("yok")) {
                                                                String sql = "UPDATE Gorev SET dogasever=?, gorev2='dogasever', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                                PreparedStatement ps = connect.prepareStatement(sql);
                                                                ps.setString(1, "g3devam");
                                                                ps.setInt(2, 0);
                                                                ps.executeUpdate();
                                                                Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                                player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Bilgili Dogasever gorevine basladiniz."));
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
                                    /* Usta Dogasever */
                                    if (affected.getType() == ItemTypes.SAPLING) {
                                        try {
                                            connect = DriverManager.getConnection(DB_URL, USER, PASS);
                                            statmt = connect.createStatement();
                                            try {
                                                statmt = connect.createStatement();
                                                String sqlara = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                                                ResultSet rsara = statmt.executeQuery(sqlara);
                                                if (rsara.next()) {
                                                    if (rsara.getString("dogasever").equals("g3bitti")) {
                                                        if(rsara.getString("gorev1").equals("yok") || rsara.getString("gorev2").equals("yok")) {
                                                            if(rsara.getString("gorev1").equals("yok")) {
                                                                String sql = "UPDATE Gorev SET dogasever=?, gorev1='dogasever', gorev1durum=? WHERE oyuncu='" + player.getName() + "'";
                                                                PreparedStatement ps = connect.prepareStatement(sql);
                                                                ps.setString(1, "g4devam");
                                                                ps.setInt(2, 0);
                                                                ps.executeUpdate();
                                                                Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                                player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Usta Dogasever gorevine basladiniz."));
                                                            } else {
                                                                if(rsara.getString("gorev2").equals("yok")) {
                                                                    String sql = "UPDATE Gorev SET dogasever=?, gorev2='dogasever', gorev2durum=? WHERE oyuncu='" + player.getName() + "'";
                                                                    PreparedStatement ps = connect.prepareStatement(sql);
                                                                    ps.setString(1, "g4devam");
                                                                    ps.setInt(2, 0);
                                                                    ps.executeUpdate();
                                                                    Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                                    player.sendMessage(Text.of(TextColors.BLUE, "[", TextColors.AQUA, "kadayifQuest", TextColors.BLUE, "] ", TextColors.DARK_RED, "Usta Dogasever gorevine basladiniz."));
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
                    if(rsara.getString("dogasever").equals("baslamadi")) {
                        setItem(1,0,1,"baslamadi","deadbush");
                        setItem(3,0,0,"baslamadi","cactus");
                        setItem(5,0,0,"baslamadi","redflower");
                        setItem(7,0,0,"baslamadi","sapling");
                    } else {
                        // Acemi Dogasever - devam ediyor
                        if(rsara.getString("dogasever").equals("g1devam")) {
                            setItem(1,0,1,"basladi","deadbush");
                            setItem(3,0,0,"baslamadi","cactus");
                            setItem(5,0,0,"baslamadi","redflower");
                            setItem(7,0,0,"baslamadi","sapling");
                        } else {
                            // Acemi Dogasever bitti
                            if(rsara.getString("dogasever").equals("g1bitti")) {
                                setItem(1,0,1,"bitirdi","deadbush");
                                setItem(3,0,1,"baslamadi","cactus");
                                setItem(5,0,0,"baslamadi","redflower");
                                setItem(7,0,0,"baslamadi","sapling");
                            } else {
                                // Tecrübeli Dogasever devam ediyor
                                if(rsara.getString("dogasever").equals("g2devam")) {
                                    setItem(1,0,1,"bitirdi","deadbush");
                                    setItem(3,0,1,"basladi","cactus");
                                    setItem(5,0,0,"baslamadi","redflower");
                                    setItem(7,0,0,"baslamadi","sapling");
                                } else {
                                    // Tecrübeli Dogasever bitti
                                    if(rsara.getString("dogasever").equals("g2bitti")) {
                                        setItem(1,0,1,"bitirdi","deadbush");
                                        setItem(3,0,1,"bitirdi","cactus");
                                        setItem(5,0,1,"baslamadi","redflower");
                                        setItem(7,0,0,"baslamadi","sapling");
                                    } else {
                                        // Bilgili Dogasever devam ediyor
                                        if(rsara.getString("dogasever").equals("g3devam")) {
                                            setItem(1,0,1,"bitirdi","deadbush");
                                            setItem(3,0,1,"bitirdi","cactus");
                                            setItem(5,0,1,"basladi","redflower");
                                            setItem(7,0,0,"baslamadi","sapling");
                                        } else {
                                            // Bilgili Dogasever bitti
                                            if(rsara.getString("dogasever").equals("g3bitti")) {
                                                setItem(1,0,1,"bitirdi","deadbush");
                                                setItem(3,0,1,"bitirdi","cactus");
                                                setItem(5,0,1,"bitirdi","redflower");
                                                setItem(7,0,1,"baslamadi","sapling");
                                            } else {
                                                // Usta Dogasever devam ediyor
                                                if(rsara.getString("dogasever").equals("g4devam")) {
                                                    setItem(1,0,1,"bitirdi","deadbush");
                                                    setItem(3,0,1,"bitirdi","cactus");
                                                    setItem(5,0,1,"bitirdi","redflower");
                                                    setItem(7,0,1,"basladi","sapling");
                                                } else {
                                                    // Usta Dogasever bitti
                                                    if(rsara.getString("dogasever").equals("g4bitti")) {
                                                        setItem(1,0,1,"bitirdi","deadbush");
                                                        setItem(3,0,1,"bitirdi","cactus");
                                                        setItem(5,0,1,"bitirdi","redflower");
                                                        setItem(7,0,1,"bitirdi","sapling");
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
        /* Acemi Dogasever */
        if(item.equals("deadbush")) {
            if(visin == 1) {
                if(gorevdurum.equals("basladi")) {
                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                            .from(ItemStack.of((ItemType) ItemTypes.DEADBUSH))
                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Acemi Dogasever"))
                            .add(Keys.ITEM_LORE, Arrays.asList(
                                    Text.of(TextColors.AQUA, "16 bitki ek."),
                                    Text.of(TextColors.GREEN, "Oduller:"),
                                    Text.of(TextColors.BLUE, "500$"),
                                    Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                            .build());
                } else {
                    if(gorevdurum.equals("baslamadi")) {
                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                .from(ItemStack.of((ItemType) ItemTypes.DEADBUSH))
                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Acemi Dogasever"))
                                .add(Keys.ITEM_LORE, Arrays.asList(
                                        Text.of(TextColors.AQUA, "16 bitki ek."),
                                        Text.of(TextColors.GREEN, "Oduller:"),
                                        Text.of(TextColors.BLUE, "500$"),
                                        Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                .build());
                    } else {
                        if(gorevdurum.equals("bitirdi")) {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) ItemTypes.DEADBUSH))
                                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.WHITE, "Acemi Dogasever"))
                                    .add(Keys.ITEM_LORE, Arrays.asList(
                                            Text.of(TextColors.AQUA, "16 bitki ek."),
                                            Text.of(TextColors.GREEN, "Oduller:"),
                                            Text.of(TextColors.BLUE, "500$"),
                                            Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                    .build());
                        }
                    }
                }
            } else {
                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                        .from(ItemStack.of((ItemType) ItemTypes.DEADBUSH))
                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "???"))
                        .add(Keys.ITEM_LORE, Arrays.asList(
                                Text.of(TextColors.AQUA, "?"),
                                Text.of(TextColors.GREEN, "?"),
                                Text.of(TextColors.BLUE, "?"),
                                Text.of(TextColors.YELLOW, "Goreve baslamak icin onceki gorevi bitir.")))
                        .build());
            }
        } else {
            /* Tecrübeli Dogasever */
            if(item.equals("cactus")) {
                if(visin == 1) {
                    if(gorevdurum.equals("basladi")) {
                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                .from(ItemStack.of((ItemType) ItemTypes.CACTUS))
                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "Tecrubeli Dogasever"))
                                .add(Keys.ITEM_LORE, Arrays.asList(
                                        Text.of(TextColors.AQUA, "32 bitki ek."),
                                        Text.of(TextColors.GREEN, "Oduller:"),
                                        Text.of(TextColors.BLUE, "1.000$"),
                                        Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                                .build());
                    } else {
                        if(gorevdurum.equals("baslamadi")) {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) ItemTypes.CACTUS))
                                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "Tecrubeli Dogasever"))
                                    .add(Keys.ITEM_LORE, Arrays.asList(
                                            Text.of(TextColors.AQUA, "32 bitki ek."),
                                            Text.of(TextColors.GREEN, "Oduller:"),
                                            Text.of(TextColors.BLUE, "1.000$"),
                                            Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                    .build());
                        } else {
                            if(gorevdurum.equals("bitirdi")) {
                                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                        .from(ItemStack.of((ItemType) ItemTypes.CACTUS))
                                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "Tecrubeli Dogasever"))
                                        .add(Keys.ITEM_LORE, Arrays.asList(
                                                Text.of(TextColors.AQUA, "32 bitki ek."),
                                                Text.of(TextColors.GREEN, "Oduller:"),
                                                Text.of(TextColors.BLUE, "1.000$"),
                                                Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                        .build());
                            }
                        }
                    }
                } else {
                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                            .from(ItemStack.of((ItemType) ItemTypes.CACTUS))
                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.BLUE, "???"))
                            .add(Keys.ITEM_LORE, Arrays.asList(
                                    Text.of(TextColors.AQUA, "?"),
                                    Text.of(TextColors.GREEN, "?"),
                                    Text.of(TextColors.BLUE, "?"),
                                    Text.of(TextColors.YELLOW, "Goreve baslamak icin onceki gorevi bitir.")))
                            .build());
                }
            } else {
                /* Bilgili Dogasever */
                if(item.equals("redflower")) {
                    if(visin == 1) {
                        if(gorevdurum.equals("basladi")) {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) ItemTypes.RED_FLOWER))
                                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "Bilgili Dogasever"))
                                    .add(Keys.ITEM_LORE, Arrays.asList(
                                            Text.of(TextColors.AQUA, "64 bitki ek."),
                                            Text.of(TextColors.GREEN, "Oduller:"),
                                            Text.of(TextColors.BLUE, "2.000$"),
                                            Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                                    .build());
                        } else {
                            if(gorevdurum.equals("baslamadi")) {
                                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                        .from(ItemStack.of((ItemType) ItemTypes.RED_FLOWER))
                                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "Bilgili Dogasever"))
                                        .add(Keys.ITEM_LORE, Arrays.asList(
                                                Text.of(TextColors.AQUA, "64 bitki ek."),
                                                Text.of(TextColors.GREEN, "Oduller:"),
                                                Text.of(TextColors.BLUE, "2.000$"),
                                                Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                        .build());
                            } else {
                                if(gorevdurum.equals("bitirdi")) {
                                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                            .from(ItemStack.of((ItemType) ItemTypes.RED_FLOWER))
                                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "Bilgili Dogasever"))
                                            .add(Keys.ITEM_LORE, Arrays.asList(
                                                    Text.of(TextColors.AQUA, "64 bitki ek."),
                                                    Text.of(TextColors.GREEN, "Oduller:"),
                                                    Text.of(TextColors.BLUE, "2.000$"),
                                                    Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                            .build());
                                }
                            }
                        }
                    } else {
                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                .from(ItemStack.of((ItemType) ItemTypes.RED_FLOWER))
                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, "???"))
                                .add(Keys.ITEM_LORE, Arrays.asList(
                                        Text.of(TextColors.AQUA, "?"),
                                        Text.of(TextColors.GREEN, "?"),
                                        Text.of(TextColors.BLUE, "?"),
                                        Text.of(TextColors.YELLOW, "Goreve baslamak icin onceki gorevi bitir.")))
                                .build());
                    }
                } else {
                    /* Usta Dogasever */
                    if(item.equals("sapling")) {
                        if(visin == 1) {
                            if(gorevdurum.equals("basladi")) {
                                inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                        .from(ItemStack.of((ItemType) ItemTypes.SAPLING))
                                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Usta Dogasever"))
                                        .add(Keys.ITEM_LORE, Arrays.asList(
                                                Text.of(TextColors.AQUA, "128 bitki ek."),
                                                Text.of(TextColors.GREEN, "Oduller:"),
                                                Text.of(TextColors.BLUE, "3.000$"),
                                                Text.of(TextColors.YELLOW, "Goreve zaten basladiniz.")))
                                        .build());
                            } else {
                                if(gorevdurum.equals("baslamadi")) {
                                    inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                            .from(ItemStack.of((ItemType) ItemTypes.SAPLING))
                                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Usta Dogasever"))
                                            .add(Keys.ITEM_LORE, Arrays.asList(
                                                    Text.of(TextColors.AQUA, "128 bitki ek."),
                                                    Text.of(TextColors.GREEN, "Oduller:"),
                                                    Text.of(TextColors.BLUE, "3.000$"),
                                                    Text.of(TextColors.YELLOW, "Goreve baslamak icin tikla.")))
                                            .build());
                                } else {
                                    if(gorevdurum.equals("bitirdi")) {
                                        inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                                .from(ItemStack.of((ItemType) ItemTypes.SAPLING))
                                                .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Usta Dogasever"))
                                                .add(Keys.ITEM_LORE, Arrays.asList(
                                                        Text.of(TextColors.AQUA, "128 bitki ek."),
                                                        Text.of(TextColors.GREEN, "Oduller:"),
                                                        Text.of(TextColors.BLUE, "3.000$"),
                                                        Text.of(TextColors.YELLOW, "Gorevi zaten bitirdiniz.")))
                                                .build());
                                    }
                                }
                            }
                        } else {
                            inventory.query(SlotPos.of(x, y)).set(ItemStack.builder()
                                    .from(ItemStack.of((ItemType) ItemTypes.SAPLING))
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