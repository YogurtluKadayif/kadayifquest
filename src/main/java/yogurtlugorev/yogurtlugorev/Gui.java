package yogurtlugorev.yogurtlugorev;

import com.google.common.collect.Iterables;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import com.pixelmonmod.pixelmon.config.PixelmonItems;
import com.pixelmonmod.pixelmon.config.PixelmonItemsHeld;
import com.pixelmonmod.pixelmon.config.PixelmonItemsPokeballs;
import com.pixelmonmod.pixelmon.items.ItemPixelmonSprite;
import net.minecraft.init.Items;
import org.spongepowered.api.Sponge;
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
import org.spongepowered.common.item.inventory.util.ItemStackUtil;
import yogurtlugorev.yogurtlugorev.guis.*;

import java.sql.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Gui {
    public Inventory inventory;
    Connection connect = null;
    Statement statmt = null;
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL3 = "jdbc:h2:./kadayifdb/kadayifquest";
    static final String USER = "sa";
    static final String PASS = "";

    public Gui(Player player) {
        this.inventory = Inventory.builder()
                .of(InventoryArchetypes.DOUBLE_CHEST)
                .property(InventoryTitle.PROPERTY_NAME, InventoryTitle.of(Text.of(TextColors.AQUA, "KadayifQuest")))
                .property("inventorydimension", InventoryDimension.of(9, 2))
                .listener(ClickInventoryEvent.class, (ClickInventoryEvent event) -> {
                    event.setCancelled(true);
                    ItemStack affected;
                    for(SlotTransaction slotTransaction : event.getTransactions()) {
                        Slot slot = slotTransaction.getSlot();
                        Container container = event.getTargetInventory();
                        SlotIndex pos = Iterables.getOnlyElement(slot.parent().getProperties(slot, SlotIndex.class));
                            affected = event.getTransactions().get(0).getOriginal().createStack();
                            if(affected.getType() == PixelmonItemsPokeballs.masterBall) {
                                final GuiPokeHunt gui = new GuiPokeHunt(player);
                                Sponge.getScheduler().createTaskBuilder()
                                        .execute(() -> player.closeInventory())
                                        .delay(1, TimeUnit.SECONDS)
                                        .submit(Yogurtlugorev.instance);
                                Sponge.getScheduler().createTaskBuilder()
                                        .execute(() -> player.openInventory(gui.getbackpack()))
                                        .delay(2, TimeUnit.SECONDS)
                                        .submit(Yogurtlugorev.instance);
                            } else {
                                if(affected.getType() == PixelmonItems.itemPixelmonSprite) {
                                    final GuiPokeBreed gui = new GuiPokeBreed(player);
                                    Sponge.getScheduler().createTaskBuilder()
                                            .execute(() -> player.closeInventory())
                                            .delay(1, TimeUnit.SECONDS)
                                            .submit(Yogurtlugorev.instance);
                                    Sponge.getScheduler().createTaskBuilder()
                                            .execute(() -> player.openInventory(gui.getbackpack()))
                                            .delay(2, TimeUnit.SECONDS)
                                            .submit(Yogurtlugorev.instance);
                                } else {
                                    if(affected.getType() == PixelmonItemsHeld.expertBelt) {
                                        final GuiPokeAzaltici gui = new GuiPokeAzaltici(player);
                                        Sponge.getScheduler().createTaskBuilder()
                                                .execute(() -> player.closeInventory())
                                                .delay(1, TimeUnit.SECONDS)
                                                .submit(Yogurtlugorev.instance);
                                        Sponge.getScheduler().createTaskBuilder()
                                                .execute(() -> player.openInventory(gui.getbackpack()))
                                                .delay(2, TimeUnit.SECONDS)
                                                .submit(Yogurtlugorev.instance);
                                    } else {
                                        if(affected.getType() == PixelmonItems.fireStone) {
                                            final GuiEvolution gui = new GuiEvolution(player);
                                            Sponge.getScheduler().createTaskBuilder()
                                                    .execute(() -> player.closeInventory())
                                                    .delay(1, TimeUnit.SECONDS)
                                                    .submit(Yogurtlugorev.instance);
                                            Sponge.getScheduler().createTaskBuilder()
                                                    .execute(() -> player.openInventory(gui.getbackpack()))
                                                    .delay(2, TimeUnit.SECONDS)
                                                    .submit(Yogurtlugorev.instance);
                                        } else {
                                            if(affected.getType() == PixelmonItems.shinyStone) {
                                                final GuiShinyHunter gui = new GuiShinyHunter(player);
                                                Sponge.getScheduler().createTaskBuilder()
                                                        .execute(() -> player.closeInventory())
                                                        .delay(1, TimeUnit.SECONDS)
                                                        .submit(Yogurtlugorev.instance);
                                                Sponge.getScheduler().createTaskBuilder()
                                                        .execute(() -> player.openInventory(gui.getbackpack()))
                                                        .delay(2, TimeUnit.SECONDS)
                                                        .submit(Yogurtlugorev.instance);
                                            } else {
                                                if(affected.getType() == PixelmonItemsHeld.powerHerb) {
                                                    final GuiLegHunter gui = new GuiLegHunter(player);
                                                    Sponge.getScheduler().createTaskBuilder()
                                                            .execute(() -> player.closeInventory())
                                                            .delay(1, TimeUnit.SECONDS)
                                                            .submit(Yogurtlugorev.instance);
                                                    Sponge.getScheduler().createTaskBuilder()
                                                            .execute(() -> player.openInventory(gui.getbackpack()))
                                                            .delay(2, TimeUnit.SECONDS)
                                                            .submit(Yogurtlugorev.instance);
                                                } else {
                                                    if(affected.getType() == PixelmonItemsHeld.splashPlate) {
                                                        final GuiBossHunter gui = new GuiBossHunter(player);
                                                        Sponge.getScheduler().createTaskBuilder()
                                                                .execute(() -> player.closeInventory())
                                                                .delay(1, TimeUnit.SECONDS)
                                                                .submit(Yogurtlugorev.instance);
                                                        Sponge.getScheduler().createTaskBuilder()
                                                                .execute(() -> player.openInventory(gui.getbackpack()))
                                                                .delay(2, TimeUnit.SECONDS)
                                                                .submit(Yogurtlugorev.instance);
                                                    } else {
                                                        if(affected.getType() == Items.MELON) { // PixelmonItemsHeld.blastoisinite
                                                            final GuiMegaHunter gui = new GuiMegaHunter(player);
                                                            Sponge.getScheduler().createTaskBuilder()
                                                                    .execute(() -> player.closeInventory())
                                                                    .delay(1, TimeUnit.SECONDS)
                                                                    .submit(Yogurtlugorev.instance);
                                                            Sponge.getScheduler().createTaskBuilder()
                                                                    .execute(() -> player.openInventory(gui.getbackpack()))
                                                                    .delay(2, TimeUnit.SECONDS)
                                                                    .submit(Yogurtlugorev.instance);
                                                        } else {
                                                            if(affected.getType() == Items.FISH) {
                                                                final GuiBalikci gui = new GuiBalikci(player);
                                                                Sponge.getScheduler().createTaskBuilder()
                                                                        .execute(() -> player.closeInventory())
                                                                        .delay(1, TimeUnit.SECONDS)
                                                                        .submit(Yogurtlugorev.instance);
                                                                Sponge.getScheduler().createTaskBuilder()
                                                                        .execute(() -> player.openInventory(gui.getbackpack()))
                                                                        .delay(2, TimeUnit.SECONDS)
                                                                        .submit(Yogurtlugorev.instance);
                                                            } else {
                                                                if(affected.getType() == Items.DIAMOND_PICKAXE) {
                                                                    final GuiMadenci gui = new GuiMadenci(player);
                                                                    Sponge.getScheduler().createTaskBuilder()
                                                                            .execute(() -> player.closeInventory())
                                                                            .delay(1, TimeUnit.SECONDS)
                                                                            .submit(Yogurtlugorev.instance);
                                                                    Sponge.getScheduler().createTaskBuilder()
                                                                            .execute(() -> player.openInventory(gui.getbackpack()))
                                                                            .delay(2, TimeUnit.SECONDS)
                                                                            .submit(Yogurtlugorev.instance);
                                                                } else {
                                                                    if(affected.getType() == Items.DIAMOND_AXE) {
                                                                        final GuiOduncu gui = new GuiOduncu(player);
                                                                        Sponge.getScheduler().createTaskBuilder()
                                                                                .execute(() -> player.closeInventory())
                                                                                .delay(1, TimeUnit.SECONDS)
                                                                                .submit(Yogurtlugorev.instance);
                                                                        Sponge.getScheduler().createTaskBuilder()
                                                                                .execute(() -> player.openInventory(gui.getbackpack()))
                                                                                .delay(2, TimeUnit.SECONDS)
                                                                                .submit(Yogurtlugorev.instance);
                                                                    } else {
                                                                        if(affected.getType() == Items.FLOWER_POT) {
                                                                            final GuiDogasever gui = new GuiDogasever(player);
                                                                            Sponge.getScheduler().createTaskBuilder()
                                                                                    .execute(() -> player.closeInventory())
                                                                                    .delay(1, TimeUnit.SECONDS)
                                                                                    .submit(Yogurtlugorev.instance);
                                                                            Sponge.getScheduler().createTaskBuilder()
                                                                                    .execute(() -> player.openInventory(gui.getbackpack()))
                                                                                    .delay(2, TimeUnit.SECONDS)
                                                                                    .submit(Yogurtlugorev.instance);
                                                                        } else {
                                                                            /*if(affected.getType() == Items.BOOK) {
                                                                                inventory.clear();
                                                                                inventory.query(SlotPos.of(1, 0)).set(ItemStack.builder()
                                                                                        .from(ItemStack.of((ItemType) PixelmonItems.tradePanel))
                                                                                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Pokemon Koleksiyoncusu"))
                                                                                        .add(Keys.ITEM_LORE, Arrays.asList(
                                                                                                Text.of(TextColors.AQUA, "Pokedex tamamlama gorevlerini gosterir.")))
                                                                                        .build());
                                                                            } else {
                                                                                if(affected.getType() == PixelmonItems.tradePanel) {
                                                                                    final GuiPokedex gui = new GuiPokedex(player);
                                                                                    Sponge.getScheduler().createTaskBuilder()
                                                                                            .execute(() -> player.closeInventory())
                                                                                            .delay(1, TimeUnit.SECONDS)
                                                                                            .submit(Yogurtlugorev.instance);
                                                                                    Sponge.getScheduler().createTaskBuilder()
                                                                                            .execute(() -> player.openInventory(gui.getbackpack()))
                                                                                            .delay(2, TimeUnit.SECONDS)
                                                                                            .submit(Yogurtlugorev.instance);
                                                                                }
                                                                            }*/
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
                                }
                            }
                    }
                })
                .build(Yogurtlugorev.instance);

        // -- 1. satır --- //
        /*inventory.query(SlotPos.of(0, 0)).set(ItemStack.builder()
                .from(ItemStack.of(ItemTypes.STAINED_GLASS_PANE))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "kadayifQuest"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "--KadayifQuest--")))
                .build());*/
        /* Poke Avcısı */
        inventory.query(SlotPos.of(1, 0)).set(ItemStack.builder()
                .from(ItemStack.of((ItemType) PixelmonItemsPokeballs.masterBall))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Pokemon Avcisi"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "Pokemon yakalama gorevlerini gosterir.")))
                .build());
        /* Poke Bakıcısı */
        net.minecraft.item.ItemStack stack = null;
        ItemStack is = null;
        PokemonSpec spec = null;
        spec = PokemonSpec.from("Blissey");
        stack = ItemPixelmonSprite.getPhoto(spec.create());
        ItemStack test = ItemStackUtil.fromNative(stack);
        inventory.query(SlotPos.of(2, 0)).set(ItemStack.builder()
                .fromItemStack(test)
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Pokemon Bakicisi"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "Pokemon bakiciligi gorevlerini gosterir")))
                .build());
        /* Poke Hunter */
        inventory.query(SlotPos.of(3, 0)).set(ItemStack.builder()
                .from(ItemStack.of((ItemType) PixelmonItemsHeld.expertBelt))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.DARK_PURPLE, "Pokemon Azaltici"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.RED, "Pokemon avlama gorevlerini gosterir")))
                .build());
        /* Evolution */
        inventory.query(SlotPos.of(4, 0)).set(ItemStack.builder()
                .from(ItemStack.of((ItemType) PixelmonItems.fireStone))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.DARK_PURPLE, "Pokemon Evrimcisi"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "Pokemon evrimlestirme gorevlerini gosterir")))
                .build());
        /* Shiny Avcisi */
        inventory.query(SlotPos.of(5, 0)).set(ItemStack.builder()
                .from(ItemStack.of((ItemType) PixelmonItems.shinyStone))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.DARK_PURPLE, "Shiny Avcisi"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "Shiny pokemon avlama gorevlerini gosterir")))
                .build());
        /* Leg Avcisi */
        inventory.query(SlotPos.of(6, 0)).set(ItemStack.builder()
                .from(ItemStack.of((ItemType) PixelmonItemsHeld.powerHerb))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.DARK_PURPLE, "Leg Avcisi"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "Legendary pokemon avlama gorevlerini gosterir")))
                .build());
        /* Boss Avcisi */
        inventory.query(SlotPos.of(7, 0)).set(ItemStack.builder()
                .from(ItemStack.of((ItemType) PixelmonItemsHeld.splashPlate))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.DARK_PURPLE, "Boss Avcisi"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "Boss pokemon avlama gorevlerini gosterir")))
                .build());
        /*inventory.query(SlotPos.of(8, 0)).set(ItemStack.builder()
                .from(ItemStack.of(ItemTypes.STAINED_GLASS_PANE))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "kadayifQuest"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "--KadayifQuest--")))
                .build());*/


        // -- 2. satır --- //
        /*inventory.query(SlotPos.of(1, 1)).set(ItemStack.builder()
                .from(ItemStack.of(ItemTypes.STAINED_GLASS_PANE))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "kadayifQuest"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "--KadayifQuest--")))
                .build());*/
        inventory.query(SlotPos.of(0, 1)).set(ItemStack.builder()
                .from(ItemStack.of(ItemTypes.COMMAND_BLOCK))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.RED, "Komutlar"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "--KadayifQuest--"),
                        Text.of(TextColors.GREEN, "/quest gunluk ->", TextColors.AQUA, " Gunluk gorevi gosterir."),
                        Text.of(TextColors.GREEN, "/quest abandon <sayi> ->", TextColors.AQUA, " Gorevinizden vazgecmenizi saglar."),
                        Text.of(TextColors.GREEN, "/quest liste ->", TextColors.AQUA, " Aktif gorevlerinizi gosterir."),
                        Text.of(TextColors.RED, "kadayifQuest")))
                .build());
        /* Mega Avcisi */
        /*inventory.query(SlotPos.of(2, 1)).set(ItemStack.builder()
                .from(ItemStack.of((ItemType) PixelmonItemsHeld.blastoisinite))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.DARK_PURPLE, "Mega Avcisi"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "Mega pokemon avlama gorevlerini gosterir"),
                        Text.of(TextColors.GREEN, "kadayifQuest")))
                .build());*/

        /* Balikci */
        inventory.query(SlotPos.of(2, 1)).set(ItemStack.builder()
                .from(ItemStack.of((ItemType) Items.FISH))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.DARK_PURPLE, "Balikci"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "Balik avlama gorevlerini gosterir")))
                .build());
        /* Madenci */
        inventory.query(SlotPos.of(3, 1)).set(ItemStack.builder()
                .from(ItemStack.of((ItemType) Items.DIAMOND_PICKAXE))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.DARK_PURPLE, "Madenci"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "Madencilik gorevlerini gosterir")))
                .build());

        /*inventory.query(SlotPos.of(4, 1)).set(ItemStack.builder()
                .from(ItemStack.of(ItemTypes.STAINED_GLASS_PANE))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "kadayifQuest"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "--KadayifQuest--")))
                .build());*/

        /* Oduncu */
        inventory.query(SlotPos.of(5, 1)).set(ItemStack.builder()
                .from(ItemStack.of((ItemType) Items.DIAMOND_AXE))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.DARK_PURPLE, "Oduncu"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "Odunculuk gorevlerini gosterir")))
                .build());
        /* Bitkici */
        inventory.query(SlotPos.of(6, 1)).set(ItemStack.builder()
                .from(ItemStack.of((ItemType) Items.FLOWER_POT))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.DARK_PURPLE, "Dogasever"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "Bitki ekme gorevlerini gosterir")))
                .build());
        /*inventory.query(SlotPos.of(7, 1)).set(ItemStack.builder()
                .from(ItemStack.of(ItemTypes.STAINED_GLASS_PANE))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "kadayifQuest"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "--KadayifQuest--")))
                .build());
        inventory.query(SlotPos.of(8, 1)).set(ItemStack.builder()
                .from(ItemStack.of(ItemTypes.STAINED_GLASS_PANE))
                .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "kadayifQuest"))
                .add(Keys.ITEM_LORE, Arrays.asList(
                        Text.of(TextColors.AQUA, "--KadayifQuest--")))
                .build());*/

        try {
            connect = DriverManager.getConnection(DB_URL3, USER, PASS);
            statmt = connect.createStatement();
            try {
                Class.forName(JDBC_DRIVER);

                statmt = connect.createStatement();
                String sqlrs2 = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                ResultSet rs2 = statmt.executeQuery(sqlrs2);

                if(rs2.next()) {
                    String gorev1 = rs2.getString("gorev1");
                    String gorev2 = rs2.getString("gorev2");
                    Integer gorev1durum = rs2.getInt("gorev1durum");
                    Integer gorev2durum = rs2.getInt("gorev2durum");
                    if(gorev1.equals("pokeavcisi")) {
                        gorev1 = "Pokemon Avcisi";
                    } else {
                        if(gorev1.equals("pokebreed")) {
                            gorev1 = "Pokemon Ureticisi";
                        } else {
                            if(gorev1.equals("pokehunter")) {
                                gorev1 = "Pokemon Azaltici";
                            } else {
                                if(gorev1.equals("evolution")) {
                                    gorev1 = "Pokemon Evrimcisi";
                                } else {
                                    if(gorev1.equals("shinyavcisi")) {
                                        gorev1 = "Shiny Avcisi";
                                    } else {
                                        if(gorev1.equals("legavcisi")) {
                                            gorev1 = "Leg Avcisi";
                                        } else {
                                            if(gorev1.equals("bossavcisi")) {
                                                gorev1 = "Boss Avcisi";
                                            } else {
                                                if(gorev1.equals("balikci")) {
                                                    gorev1 = "Balikci";
                                                } else {
                                                    if(gorev1.equals("madenci")) {
                                                        gorev1 = "Madenci";
                                                    } else {
                                                        if(gorev1.equals("oduncu")) {
                                                            gorev1 = "Oduncu";
                                                        } else {
                                                            if(gorev1.equals("dogasever")) {
                                                                gorev1 = "Dogasever";
                                                            } else {
                                                                if(gorev1.equals("megaavcisi")) {
                                                                    gorev1 = "Mega Avcisi";
                                                                } else {
                                                                    if (gorev1.equals("pokedex")) {
                                                                        gorev1 = "Pokemon Koleksiyoncusu";
                                                                    } else {
                                                                        if (gorev1.equals("yok")) {
                                                                            gorev1 = "Yok";
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
                                }
                            }
                        }
                    }
                    if(gorev2.equals("pokeavcisi")) {
                        gorev2 = "Pokemon Avcisi";
                    } else {
                        if(gorev2.equals("pokebreed")) {
                            gorev2 = "Pokemon Ureticisi";
                        } else {
                            if(gorev2.equals("pokehunter")) {
                                gorev2 = "Pokemon Azaltici";
                            } else {
                                if(gorev2.equals("evolution")) {
                                    gorev2 = "Pokemon Evrimcisi";
                                } else {
                                    if(gorev2.equals("shinyavcisi")) {
                                        gorev2 = "Shiny Avcisi";
                                    } else {
                                        if(gorev2.equals("legavcisi")) {
                                            gorev2 = "Leg Avcisi";
                                        } else {
                                            if(gorev2.equals("bossavcisi")) {
                                                gorev2 = "Boss Avcisi";
                                            } else {
                                                if(gorev2.equals("balikci")) {
                                                    gorev2 = "Balikci";
                                                } else {
                                                    if(gorev2.equals("madenci")) {
                                                        gorev2 = "Madenci";
                                                    } else {
                                                        if(gorev2.equals("oduncu")) {
                                                            gorev2 = "Oduncu";
                                                        } else {
                                                            if(gorev2.equals("dogasever")) {
                                                                gorev2 = "Dogasever";
                                                            } else {
                                                                if(gorev2.equals("megaavcisi")) {
                                                                    gorev2 = "Mega Avcisi";
                                                                } else {
                                                                    if (gorev2.equals("pokedex")) {
                                                                        gorev2 = "Pokemon Koleksiyoncusu";
                                                                    } else {
                                                                        if (gorev2.equals("yok")) {
                                                                            gorev2 = "Yok";
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
                                }
                            }
                        }
                    }

                    inventory.query(SlotPos.of(8, 1)).set(ItemStack.builder()
                            .from(ItemStack.of(ItemTypes.BOOK))
                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.LIGHT_PURPLE, "Aktif Gorevleriniz"))
                            .add(Keys.ITEM_LORE, Arrays.asList(
                                    Text.of(TextColors.AQUA, "--KadayifQuest--"),
                                    Text.of(TextColors.GREEN, "Gorevleriniz"),
                                    Text.of(TextColors.AQUA, "Gorev 1: ", TextColors.GREEN, gorev1, TextColors.AQUA, " Ilerlemeniz: ", TextColors.AQUA, gorev1durum),
                                    Text.of(TextColors.AQUA, "Gorev 2: ", TextColors.GREEN, gorev2, TextColors.AQUA, " Ilerlemeniz: ", TextColors.AQUA, gorev2durum)))
                                    //Text.of(TextColors.GREEN, "->Sonraki sayfaya gecmek icin tikla.")))
                            .build());
                }

                statmt = connect.createStatement();
                String sqlrs3 = "SELECT * FROM GunlukGorev WHERE id='0'";
                ResultSet rs3 = statmt.executeQuery(sqlrs3);

                if(rs3.next()) {
                    String gunlukgorev = rs3.getString("gorev");

                    if(gunlukgorev.equals("pokeavla")) {
                        if(Integer.valueOf(rs3.getString("gorevtotalivs")) > 50) {
                            inventory.query(SlotPos.of(4, 1)).set(ItemStack.builder()
                                    .from(ItemStack.of(ItemTypes.BOOK))
                                    .add(Keys.DISPLAY_NAME, Text.of(TextColors.RED, "Gunluk Gorev"))
                                    .add(Keys.ITEM_LORE, Arrays.asList(
                                            Text.of(TextColors.AQUA, "--KadayifQuest--"),
                                            Text.of(TextColors.GREEN, "Bugunun Gorevi: Pokemon Avla"),
                                            Text.of(TextColors.AQUA, "Pokemon: ", TextColors.GREEN, rs3.getString("gorevpokemon"), TextColors.AQUA, " Minimum IVS: ", TextColors.AQUA, rs3.getString("gorevtotalivs"))))
                                            .build());
                        } else {
                            if (Integer.valueOf(rs3.getString("gorevtotalivs")) < 50) {
                                inventory.query(SlotPos.of(4, 1)).set(ItemStack.builder()
                                        .from(ItemStack.of(ItemTypes.BOOK))
                                        .add(Keys.DISPLAY_NAME, Text.of(TextColors.RED, "Gunluk Gorev"))
                                        .add(Keys.ITEM_LORE, Arrays.asList(
                                                Text.of(TextColors.AQUA, "--KadayifQuest--"),
                                                Text.of(TextColors.GREEN, "Bugunun Gorevi: Pokemon Avla"),
                                                Text.of(TextColors.AQUA, "Pokemon: ", TextColors.GREEN, rs3.getString("gorevpokemon"), TextColors.AQUA, " Maksimum IVS: ", TextColors.AQUA, rs3.getString("gorevtotalivs"))))
                                        .build());
                            }
                        }

                    }

                }

            }catch (SQLException se) {
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
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public Inventory getbackpack() {
        return this.inventory;
    }
}

