package yogurtlugorev.yogurtlugorev.events;

import net.minecraft.init.Blocks;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import yogurtlugorev.yogurtlugorev.Yogurtlugorev;

import java.sql.*;
import java.util.UUID;

public class GorevChangeBlock {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./kadayifdb/kadayifquest";
    static final String USER = "sa";
    static final String PASS = "";
    Connection connect = null;
    Statement statmt = null;
    Statement statmt2 = null;

    // Doğasever
    @Listener
    public void onPlace(ChangeBlockEvent.Place event, @First Player player) {
        try {
            connect = DriverManager.getConnection(DB_URL, USER, PASS);
            statmt = connect.createStatement();
            try {
                statmt = connect.createStatement();
                String sqlrs = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                ResultSet rs = statmt.executeQuery(sqlrs);
                Player p = Sponge.getServer().getPlayer(player.getName()).get();
                if (rs.next()) {
                    final UUID uuid = p.getUniqueId();
                    final String g1 = Yogurtlugorev.gg.get(uuid).get("gorev1durum:");
                    final String g2 = Yogurtlugorev.gg.get(uuid).get("gorev2durum:");
                    // ------------------------------------------------------ Doğasever ------------------------------------------------------
                    if (rs.getString("dogasever").equals("g1devam") || rs.getString("dogasever").equals("g2devam") || rs.getString("dogasever").equals("g3devam") || rs.getString("dogasever").equals("g4devam")) {
                        BlockSnapshot bs = event.getTransactions().get(0).getDefault();
                        if (bs.getState().getType() == Blocks.SAPLING || bs.getState().getType() == Blocks.RED_FLOWER || bs.getState().getType() == Blocks.YELLOW_FLOWER || bs.getState().getType() == Blocks.CACTUS) {
                            if (rs.getString("gorev1").equals("dogasever")) {
                                if (rs.getString("gorev1").equals("dogasever")) {
                                    Yogurtlugorev.durum = rs.getInt("gorev1durum");
                                    statmt = connect.createStatement();
                                    String sql = "UPDATE Gorev SET gorev1durum=? WHERE oyuncu='" + p.getName() + "'";
                                    PreparedStatement ps = connect.prepareStatement(sql);
                                    ps.setInt(1,(Integer.valueOf(g1) + 1));
                                    ps.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev1durum:", String.valueOf(Integer.valueOf(g1) + 1));
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g1) + 1)));
                                }
                                if (rs.getString("dogasever").equals("g1devam") && (Integer.valueOf(g1) + 1) == 16) {
                                    statmt = connect.createStatement();
                                    String sql2 = "UPDATE Gorev SET  dogasever=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                    ps2.setString(1, "g1bitti");
                                    ps2.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 500");
                                    //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Acemi Dogasever gorevini bitirdiniz, tebrikler."));
                                } else {
                                    if (rs.getString("dogasever").equals("g2devam") && (Integer.valueOf(g1) + 1) == 32) {
                                        statmt = connect.createStatement();
                                        String sql2 = "UPDATE Gorev SET  dogasever=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                        ps2.setString(1, "g2bitti");
                                        ps2.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 1000");
                                        //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Tecrubeli Dogasever gorevini bitirdiniz, tebrikler."));
                                    } else {
                                        if (rs.getString("dogasever").equals("g3devam") && (Integer.valueOf(g1) + 1) == 64) {
                                            statmt = connect.createStatement();
                                            String sql2 = "UPDATE Gorev SET  dogasever=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "g3bitti");
                                            ps2.executeUpdate();
                                            Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 2000");
                                            //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bilgili Dogasever gorevini bitirdiniz, tebrikler."));
                                        } else {
                                            if (rs.getString("dogasever").equals("g4devam") && (Integer.valueOf(g1) + 1) == 128) {
                                                statmt = connect.createStatement();
                                                String sql2 = "UPDATE Gorev SET  dogasever=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                ps2.setString(1, "g4bitti");
                                                ps2.executeUpdate();
                                                Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 3000");
                                                //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                                //-----------------------
                                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Usta Dogasever gorevini bitirdiniz, tebrikler."));
                                                Text ytext = Text.builder("[").color(TextColors.AQUA).append(
                                                        Text.builder("kadayifQuest").color(TextColors.LIGHT_PURPLE).build()).append(
                                                        Text.builder("] ").color(TextColors.AQUA).build()).append(
                                                        Text.builder(p.getName()).color(TextColors.BLUE).build()).append(
                                                        Text.builder(" isimli oyuncu Dogasever gorevini tamamladi. -> ").color(TextColors.GREEN).build()).append(
                                                        Text.builder("/quest").color(TextColors.BLUE).onHover(TextActions.showText(Text.of(TextColors.LIGHT_PURPLE, "Komutunu kullanarak gorevlere erisebilirsiniz."))).build()).append().append().build();
                                                Sponge.getServer().getBroadcastChannel().send(ytext);
                                            }
                                        }
                                    }
                                }

                            } else {
                                if (rs.getString("gorev2").equals("dogasever")) {
                                    if (rs.getString("gorev2").equals("dogasever")) {
                                        Yogurtlugorev.durum = rs.getInt("gorev2durum");
                                        statmt = connect.createStatement();
                                        String sql = "UPDATE Gorev SET gorev2durum=? WHERE oyuncu='" + p.getName() + "'";
                                        PreparedStatement ps = connect.prepareStatement(sql);
                                        ps.setInt(1,Integer.valueOf(g2) + 1);
                                        ps.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev2durum:", String.valueOf(Integer.valueOf(g2) + 1));
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g2) + 1)));
                                    }
                                    if (rs.getString("dogasever").equals("g1devam") && (Integer.valueOf(g2) + 1) == 16) {
                                        statmt = connect.createStatement();
                                        String sql2 = "UPDATE Gorev SET  dogasever=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                        ps2.setString(1, "g1bitti");
                                        ps2.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 500");
                                        //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Acemi Dogasever gorevini bitirdiniz, tebrikler."));
                                    } else {
                                        if (rs.getString("dogasever").equals("g2devam") && (Integer.valueOf(g2) + 1) == 32) {
                                            statmt = connect.createStatement();
                                            String sql2 = "UPDATE Gorev SET  dogasever=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "g2bitti");
                                            ps2.executeUpdate();
                                            Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 1000");
                                            //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Tecrubeli Dogasever gorevini bitirdiniz, tebrikler."));
                                        } else {
                                            if (rs.getString("dogasever").equals("g3devam") && (Integer.valueOf(g2) + 1) == 64) {
                                                statmt = connect.createStatement();
                                                String sql2 = "UPDATE Gorev SET  dogasever=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                ps2.setString(1, "g3bitti");
                                                ps2.executeUpdate();
                                                Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 2000");
                                                //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bilgili Dogasever gorevini bitirdiniz, tebrikler."));
                                            } else {
                                                if (rs.getString("dogasever").equals("g4devam") && (Integer.valueOf(g2) + 1) == 128) {
                                                    statmt = connect.createStatement();
                                                    String sql2 = "UPDATE Gorev SET  dogasever=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                    ps2.setString(1, "g4bitti");
                                                    ps2.executeUpdate();
                                                    Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 3000");
                                                    //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Usta Dogasever gorevini bitirdiniz, tebrikler."));
                                                    Text ytext = Text.builder("[").color(TextColors.AQUA).append(
                                                            Text.builder("kadayifQuest").color(TextColors.LIGHT_PURPLE).build()).append(
                                                            Text.builder("] ").color(TextColors.AQUA).build()).append(
                                                            Text.builder(p.getName()).color(TextColors.BLUE).build()).append(
                                                            Text.builder(" isimli oyuncu Dogasever gorevini tamamladi. -> ").color(TextColors.GREEN).build()).append(
                                                            Text.builder("/quest").color(TextColors.BLUE).onHover(TextActions.showText(Text.of(TextColors.LIGHT_PURPLE, "Komutunu kullanarak gorevlere erisebilirsiniz."))).build()).append().append().build();
                                                    Sponge.getServer().getBroadcastChannel().send(ytext);
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

    // Madenci, Oduncu, Doğasever
    @Listener
    public void onBreak(ChangeBlockEvent.Break event, @First Player player) {
        try {
            connect = DriverManager.getConnection(DB_URL, USER, PASS);
            statmt = connect.createStatement();
            try {
                statmt = connect.createStatement();
                String sqlrs = "SELECT * FROM Gorev WHERE oyuncu='" + player.getName() + "'";
                ResultSet rs = statmt.executeQuery(sqlrs);
                Player p = Sponge.getServer().getPlayer(player.getName()).get();
                if (rs.next()) {
                    final UUID uuid = p.getUniqueId();
                    final String g1 = Yogurtlugorev.gg.get(uuid).get("gorev1durum:");
                    final String g2 = Yogurtlugorev.gg.get(uuid).get("gorev2durum:");
                    // ------------------------------------------------------ Madenci ------------------------------------------------------
                    if (rs.getString("madenci").equals("g1devam") || rs.getString("madenci").equals("g2devam") || rs.getString("madenci").equals("g3devam") || rs.getString("madenci").equals("g4devam")) {
                        BlockSnapshot bs = event.getTransactions().get(0).getOriginal();
                        if (bs.getState().getType() == Blocks.IRON_ORE || bs.getState().getType() == Blocks.COAL_ORE || bs.getState().getType() == Blocks.GOLD_ORE || bs.getState().getType() == Blocks.DIAMOND_ORE) {
                            if (rs.getString("gorev1").equals("madenci")) {
                                if (rs.getString("gorev1").equals("madenci") && rs.getString("madenci").equals("g1devam") && bs.getState().getType() == Blocks.COAL_ORE) {
                                    Yogurtlugorev.durum = rs.getInt("gorev1durum");
                                    statmt = connect.createStatement();
                                    String sql = "UPDATE Gorev SET gorev1durum=? WHERE oyuncu='" + p.getName() + "'";
                                    PreparedStatement ps = connect.prepareStatement(sql);
                                    ps.setInt(1, Integer.valueOf(g1) + 1);
                                    ps.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev1durum:", String.valueOf(Integer.valueOf(g1) + 1));
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g1) + 1)));
                                } else {
                                    if (rs.getString("gorev1").equals("madenci") && rs.getString("madenci").equals("g2devam") && bs.getState().getType() == Blocks.IRON_ORE) {
                                        Yogurtlugorev.durum = rs.getInt("gorev1durum");
                                        statmt = connect.createStatement();
                                        String sql = "UPDATE Gorev SET gorev1durum=? WHERE oyuncu='" + p.getName() + "'";
                                        PreparedStatement ps = connect.prepareStatement(sql);
                                        ps.setInt(1, Integer.valueOf(g1) + 1);
                                        ps.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev1durum:", String.valueOf(Integer.valueOf(g1) + 1));
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g1) + 1)));
                                    } else {
                                        if (rs.getString("gorev1").equals("madenci") && rs.getString("madenci").equals("g3devam") && bs.getState().getType() == Blocks.GOLD_ORE) {
                                            Yogurtlugorev.durum = rs.getInt("gorev1durum");
                                            statmt = connect.createStatement();
                                            String sql = "UPDATE Gorev SET gorev1durum=? WHERE oyuncu='" + p.getName() + "'";
                                            PreparedStatement ps = connect.prepareStatement(sql);
                                            ps.setInt(1, Integer.valueOf(g1) + 1);
                                            ps.executeUpdate();
                                            Yogurtlugorev.gg.get(uuid).put("gorev1durum:", String.valueOf(Integer.valueOf(g1) + 1));
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g1) + 1)));
                                        } else {
                                            if (rs.getString("gorev1").equals("madenci") && rs.getString("madenci").equals("g4devam") && bs.getState().getType() == Blocks.DIAMOND_ORE) {
                                                Yogurtlugorev.durum = rs.getInt("gorev1durum");
                                                statmt = connect.createStatement();
                                                String sql = "UPDATE Gorev SET gorev1durum=? WHERE oyuncu='" + p.getName() + "'";
                                                PreparedStatement ps = connect.prepareStatement(sql);
                                                ps.setInt(1, Integer.valueOf(g1) + 1);
                                                ps.executeUpdate();
                                                Yogurtlugorev.gg.get(uuid).put("gorev1durum:", String.valueOf(Integer.valueOf(g1) + 1));
                                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g1) + 1)));
                                            }
                                        }
                                    }
                                }
                                if (rs.getString("madenci").equals("g1devam") && (Integer.valueOf(g1) + 1) == 64) {
                                    statmt = connect.createStatement();
                                    String sql2 = "UPDATE Gorev SET  madenci=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                    ps2.setString(1, "g1bitti");
                                    ps2.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 500");
                                    //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Acemi Madenci gorevini bitirdiniz, tebrikler."));
                                } else {
                                    if (rs.getString("madenci").equals("g2devam") && (Integer.valueOf(g1) + 1) == 64) {
                                        statmt = connect.createStatement();
                                        String sql2 = "UPDATE Gorev SET  madenci=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                        ps2.setString(1, "g2bitti");
                                        ps2.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 1000");
                                        //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Tecrubeli Madenci gorevini bitirdiniz, tebrikler."));
                                    } else {
                                        if (rs.getString("madenci").equals("g3devam") && (Integer.valueOf(g1) + 1) == 64) {
                                            statmt = connect.createStatement();
                                            String sql2 = "UPDATE Gorev SET  madenci=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "g3bitti");
                                            ps2.executeUpdate();
                                            Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 2000");
                                            //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bilgili Madenci gorevini bitirdiniz, tebrikler."));
                                        } else {
                                            if (rs.getString("madenci").equals("g4devam") && (Integer.valueOf(g1) + 1) == 64) {
                                                statmt = connect.createStatement();
                                                String sql2 = "UPDATE Gorev SET  madenci=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                ps2.setString(1, "g4bitti");
                                                ps2.executeUpdate();
                                                Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 3000");
                                                //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                                //---------------
                                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Usta Madenci gorevini bitirdiniz, tebrikler."));
                                                Text ytext = Text.builder("[").color(TextColors.AQUA).append(
                                                        Text.builder("kadayifQuest").color(TextColors.LIGHT_PURPLE).build()).append(
                                                        Text.builder("] ").color(TextColors.AQUA).build()).append(
                                                        Text.builder(p.getName()).color(TextColors.BLUE).build()).append(
                                                        Text.builder(" isimli oyuncu Madenci gorevini tamamladi. -> ").color(TextColors.GREEN).build()).append(
                                                        Text.builder("/quest").color(TextColors.BLUE).onHover(TextActions.showText(Text.of(TextColors.LIGHT_PURPLE, "Komutunu kullanarak gorevlere erisebilirsiniz."))).build()).append().append().build();
                                                Sponge.getServer().getBroadcastChannel().send(ytext);
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (rs.getString("gorev2").equals("madenci")) {
                                    /*if (rs.getString("gorev2").equals("madenci")) {
                                        Yogurtlugorev.durum = rs.getInt("gorev2durum");
                                        statmt = connect.createStatement();
                                        String sql = "UPDATE Gorev SET gorev2durum=? WHERE oyuncu='" + p.getName() + "'";
                                        PreparedStatement ps = connect.prepareStatement(sql);
                                        ps.setInt(1, Yogurtlugorev.durum + 1);
                                        ps.executeUpdate();
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Yogurtlugorev.durum + 1)));
                                    }*/
                                    if (rs.getString("gorev2").equals("madenci") && rs.getString("madenci").equals("g1devam") && bs.getState().getType() == Blocks.COAL_ORE) {
                                        Yogurtlugorev.durum = rs.getInt("gorev2durum");
                                        statmt = connect.createStatement();
                                        String sql = "UPDATE Gorev SET gorev2durum=? WHERE oyuncu='" + p.getName() + "'";
                                        PreparedStatement ps = connect.prepareStatement(sql);
                                        ps.setInt(1, Integer.valueOf(g2) + 1);
                                        ps.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev2durum:", String.valueOf(Integer.valueOf(g2) + 1));
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g2) + 1)));
                                    } else {
                                        if (rs.getString("gorev2").equals("madenci") && rs.getString("madenci").equals("g2devam") && bs.getState().getType() == Blocks.IRON_ORE) {
                                            Yogurtlugorev.durum = rs.getInt("gorev2durum");
                                            statmt = connect.createStatement();
                                            String sql = "UPDATE Gorev SET gorev2durum=? WHERE oyuncu='" + p.getName() + "'";
                                            PreparedStatement ps = connect.prepareStatement(sql);
                                            ps.setInt(1, Integer.valueOf(g2) + 1);
                                            ps.executeUpdate();
                                            Yogurtlugorev.gg.get(uuid).put("gorev2durum:", String.valueOf(Integer.valueOf(g2) + 1));
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g2) + 1)));
                                        } else {
                                            if (rs.getString("gorev2").equals("madenci") && rs.getString("madenci").equals("g3devam") && bs.getState().getType() == Blocks.GOLD_ORE) {
                                                Yogurtlugorev.durum = rs.getInt("gorev2durum");
                                                statmt = connect.createStatement();
                                                String sql = "UPDATE Gorev SET gorev2durum=? WHERE oyuncu='" + p.getName() + "'";
                                                PreparedStatement ps = connect.prepareStatement(sql);
                                                ps.setInt(1, Integer.valueOf(g2) + 1);
                                                ps.executeUpdate();
                                                Yogurtlugorev.gg.get(uuid).put("gorev2durum:", String.valueOf(Integer.valueOf(g2) + 1));
                                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g2) + 1)));
                                            } else {
                                                if (rs.getString("gorev2").equals("madenci") && rs.getString("madenci").equals("g4devam") && bs.getState().getType() == Blocks.DIAMOND_ORE) {
                                                    Yogurtlugorev.durum = rs.getInt("gorev2durum");
                                                    statmt = connect.createStatement();
                                                    String sql = "UPDATE Gorev SET gorev2durum=? WHERE oyuncu='" + p.getName() + "'";
                                                    PreparedStatement ps = connect.prepareStatement(sql);
                                                    ps.setInt(1, Integer.valueOf(g2) + 1);
                                                    ps.executeUpdate();
                                                    Yogurtlugorev.gg.get(uuid).put("gorev2durum:", String.valueOf(Integer.valueOf(g2) + 1));
                                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g2) + 1)));
                                                }
                                            }
                                        }
                                    }
                                    if (rs.getString("madenci").equals("g1devam") && (Integer.valueOf(g2) + 1) == 64) {
                                        statmt = connect.createStatement();
                                        String sql2 = "UPDATE Gorev SET  madenci=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                        ps2.setString(1, "g1bitti");
                                        ps2.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 500");
                                        //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Acemi Madenci gorevini bitirdiniz, tebrikler."));
                                    } else {
                                        if (rs.getString("madenci").equals("g2devam") && (Integer.valueOf(g2) + 1) == 64) {
                                            statmt = connect.createStatement();
                                            String sql2 = "UPDATE Gorev SET  madenci=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "g2bitti");
                                            ps2.executeUpdate();
                                            Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 1000");
                                            //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Tecrubeli Madenci gorevini bitirdiniz, tebrikler."));
                                        } else {
                                            if (rs.getString("madenci").equals("g3devam") && (Integer.valueOf(g2) + 1) == 64) {
                                                statmt = connect.createStatement();
                                                String sql2 = "UPDATE Gorev SET  madenci=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                ps2.setString(1, "g3bitti");
                                                ps2.executeUpdate();
                                                Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 2000");
                                                //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bilgili Madenci gorevini bitirdiniz, tebrikler."));
                                            } else {
                                                if (rs.getString("madenci").equals("g4devam") && (Integer.valueOf(g2) + 1) == 64) {
                                                    statmt = connect.createStatement();
                                                    String sql2 = "UPDATE Gorev SET  madenci=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                    ps2.setString(1, "g4bitti");
                                                    ps2.executeUpdate();
                                                    Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 3000");
                                                    //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                                    //-----------------------
                                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Usta Madenci gorevini bitirdiniz, tebrikler."));
                                                    Text ytext = Text.builder("[").color(TextColors.AQUA).append(
                                                            Text.builder("kadayifQuest").color(TextColors.LIGHT_PURPLE).build()).append(
                                                            Text.builder("] ").color(TextColors.AQUA).build()).append(
                                                            Text.builder(p.getName()).color(TextColors.BLUE).build()).append(
                                                            Text.builder(" isimli oyuncu Madenci gorevini tamamladi. -> ").color(TextColors.GREEN).build()).append(
                                                            Text.builder("/quest").color(TextColors.BLUE).onHover(TextActions.showText(Text.of(TextColors.LIGHT_PURPLE, "Komutunu kullanarak gorevlere erisebilirsiniz."))).build()).append().append().build();
                                                    Sponge.getServer().getBroadcastChannel().send(ytext);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // ------------------------------------------------------ Oduncu ------------------------------------------------------
                    if (rs.getString("oduncu").equals("g1devam") || rs.getString("oduncu").equals("g2devam") || rs.getString("oduncu").equals("g3devam") || rs.getString("oduncu").equals("g4devam")) {
                        BlockSnapshot bs = event.getTransactions().get(0).getOriginal();
                        if (bs.getState().getType() == Blocks.LOG) {
                            if (rs.getString("gorev1").equals("oduncu")) {
                                if (rs.getString("gorev1").equals("oduncu")) {
                                    Yogurtlugorev.durum = rs.getInt("gorev1durum");
                                    statmt = connect.createStatement();
                                    String sql = "UPDATE Gorev SET gorev1durum=? WHERE oyuncu='" + p.getName() + "'";
                                    PreparedStatement ps = connect.prepareStatement(sql);
                                    ps.setInt(1, Integer.valueOf(g1) + 1);
                                    ps.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev1durum:", String.valueOf(Integer.valueOf(g1) + 1));
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g1) + 1)));
                                }
                                if (rs.getString("oduncu").equals("g1devam") && (Integer.valueOf(g1) + 1) == 16) {
                                    statmt = connect.createStatement();
                                    String sql2 = "UPDATE Gorev SET  oduncu=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                    ps2.setString(1, "g1bitti");
                                    ps2.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 500");
                                    //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Acemi Oduncu gorevini bitirdiniz, tebrikler."));
                                } else {
                                    if (rs.getString("oduncu").equals("g2devam") && (Integer.valueOf(g1) + 1) == 32) {
                                        statmt = connect.createStatement();
                                        String sql2 = "UPDATE Gorev SET  oduncu=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                        ps2.setString(1, "g2bitti");
                                        ps2.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 1000");
                                        //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Tecrubeli Oduncu gorevini bitirdiniz, tebrikler."));
                                    } else {
                                        if (rs.getString("oduncu").equals("g3devam") && (Integer.valueOf(g1) + 1) == 64) {
                                            statmt = connect.createStatement();
                                            String sql2 = "UPDATE Gorev SET  oduncu=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "g3bitti");
                                            ps2.executeUpdate();
                                            Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 2000");
                                            //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bilgili Oduncu gorevini bitirdiniz, tebrikler."));
                                        } else {
                                            if (rs.getString("oduncu").equals("g4devam") && (Integer.valueOf(g1) + 1) == 128) {
                                                statmt = connect.createStatement();
                                                String sql2 = "UPDATE Gorev SET  oduncu=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                ps2.setString(1, "g4bitti");
                                                ps2.executeUpdate();
                                                Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 3000");
                                                //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                                //-----------------
                                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Usta Oduncu gorevini bitirdiniz, tebrikler."));
                                                Text ytext = Text.builder("[").color(TextColors.AQUA).append(
                                                        Text.builder("kadayifQuest").color(TextColors.LIGHT_PURPLE).build()).append(
                                                        Text.builder("] ").color(TextColors.AQUA).build()).append(
                                                        Text.builder(p.getName()).color(TextColors.BLUE).build()).append(
                                                        Text.builder(" isimli oyuncu Oduncu gorevini tamamladi. -> ").color(TextColors.GREEN).build()).append(
                                                        Text.builder("/quest").color(TextColors.BLUE).onHover(TextActions.showText(Text.of(TextColors.LIGHT_PURPLE, "Komutunu kullanarak gorevlere erisebilirsiniz."))).build()).append().append().build();
                                                Sponge.getServer().getBroadcastChannel().send(ytext);
                                            }
                                        }
                                    }
                                }
                            } else {
                                if (rs.getString("gorev2").equals("oduncu")) {
                                    if (rs.getString("gorev2").equals("oduncu")) {
                                        Yogurtlugorev.durum = rs.getInt("gorev2durum");
                                        statmt = connect.createStatement();
                                        String sql = "UPDATE Gorev SET gorev2durum=? WHERE oyuncu='" + p.getName() + "'";
                                        PreparedStatement ps = connect.prepareStatement(sql);
                                        ps.setInt(1, Integer.valueOf(g2) + 1);
                                        ps.executeUpdate();
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g2) + 1)));
                                    }
                                    if (rs.getString("oduncu").equals("g1devam") && (Integer.valueOf(g2) + 1) == 16) {
                                        statmt = connect.createStatement();
                                        String sql2 = "UPDATE Gorev SET  oduncu=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                        ps2.setString(1, "g1bitti");
                                        ps2.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 500");
                                        //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Acemi Oduncu gorevini bitirdiniz, tebrikler."));
                                    } else {
                                        if (rs.getString("oduncu").equals("g2devam") && (Integer.valueOf(g2) + 1) == 32) {
                                            statmt = connect.createStatement();
                                            String sql2 = "UPDATE Gorev SET  oduncu=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "g2bitti");
                                            ps2.executeUpdate();
                                            Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 1000");
                                            //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Tecrubeli Oduncu gorevini bitirdiniz, tebrikler."));
                                        } else {
                                            if (rs.getString("oduncu").equals("g3devam") && (Integer.valueOf(g2) + 1) == 64) {
                                                statmt = connect.createStatement();
                                                String sql2 = "UPDATE Gorev SET  oduncu=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                ps2.setString(1, "g3bitti");
                                                ps2.executeUpdate();
                                                Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 2000");
                                                //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bilgili Oduncu gorevini bitirdiniz, tebrikler."));
                                            } else {
                                                if (rs.getString("oduncu").equals("g4devam") && (Integer.valueOf(g2) + 1) == 128) {
                                                    statmt = connect.createStatement();
                                                    String sql2 = "UPDATE Gorev SET  oduncu=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                    ps2.setString(1, "g4bitti");
                                                    ps2.executeUpdate();
                                                    Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 3000");
                                                    //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                                    //------------------------
                                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Usta Oduncu gorevini bitirdiniz, tebrikler."));
                                                    Text ytext = Text.builder("[").color(TextColors.AQUA).append(
                                                            Text.builder("kadayifQuest").color(TextColors.LIGHT_PURPLE).build()).append(
                                                            Text.builder("] ").color(TextColors.AQUA).build()).append(
                                                            Text.builder(p.getName()).color(TextColors.BLUE).build()).append(
                                                            Text.builder(" isimli oyuncu Oduncu gorevini tamamladi. -> ").color(TextColors.GREEN).build()).append(
                                                            Text.builder("/quest").color(TextColors.BLUE).onHover(TextActions.showText(Text.of(TextColors.LIGHT_PURPLE, "Komutunu kullanarak gorevlere erisebilirsiniz."))).build()).append().append().build();
                                                    Sponge.getServer().getBroadcastChannel().send(ytext);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // ------------------------------------------------------ Doğasever ------------------------------------------------------
                    if (rs.getString("dogasever").equals("g1devam") || rs.getString("dogasever").equals("g2devam") || rs.getString("dogasever").equals("g3devam") || rs.getString("dogasever").equals("g4devam")) {
                        BlockSnapshot bs = event.getTransactions().get(0).getOriginal();
                        if (bs.getState().getType() == Blocks.SAPLING || bs.getState().getType() == Blocks.RED_FLOWER || bs.getState().getType() == Blocks.YELLOW_FLOWER || bs.getState().getType() == Blocks.CACTUS) {
                            if (rs.getString("gorev1").equals("dogasever")) {
                                if (rs.getString("gorev1").equals("dogasever")) {
                                    Yogurtlugorev.durum = rs.getInt("gorev1durum");
                                    statmt = connect.createStatement();
                                    String sql = "UPDATE Gorev SET gorev1durum=? WHERE oyuncu='" + p.getName() + "'";
                                    PreparedStatement ps = connect.prepareStatement(sql);
                                    ps.setInt(1, Integer.valueOf(g1) - 1);
                                    ps.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev1durum:", String.valueOf(Integer.valueOf(g1) - 1));
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde gerilediniz, durumunuz: " + (Integer.valueOf(g1) - 1)));
                                }
                            } else {
                                if (rs.getString("gorev2").equals("dogasever")) {
                                    if (rs.getString("gorev2").equals("dogasever")) {
                                        Yogurtlugorev.durum = rs.getInt("gorev2durum");
                                        statmt = connect.createStatement();
                                        String sql = "UPDATE Gorev SET gorev2durum=? WHERE oyuncu='" + p.getName() + "'";
                                        PreparedStatement ps = connect.prepareStatement(sql);
                                        ps.setInt(1, Integer.valueOf(g2) - 1);
                                        ps.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev2durum:", String.valueOf(Integer.valueOf(g2) - 1));
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde gerilediniz, durumunuz: " + (Integer.valueOf(g2) - 1)));
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
