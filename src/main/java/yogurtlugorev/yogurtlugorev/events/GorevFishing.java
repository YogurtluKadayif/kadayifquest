package yogurtlugorev.yogurtlugorev.events;

import com.pixelmonmod.pixelmon.api.events.FishingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import yogurtlugorev.yogurtlugorev.Yogurtlugorev;

import java.sql.*;
import java.util.UUID;

public class GorevFishing {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./kadayifdb/kadayifquest";
    static final String USER = "sa";
    static final String PASS = "";
    Connection connect = null;
    Statement statmt = null;
    Statement statmt2 = null;

    // Balıkçı
    @SubscribeEvent
    public void onFish(FishingEvent.Catch event) {
        try {
            connect = DriverManager.getConnection(DB_URL, USER, PASS);
            statmt = connect.createStatement();
            try {
                statmt = connect.createStatement();
                String sqlrs = "SELECT * FROM Gorev WHERE oyuncu='" + event.player.getName() + "'";
                ResultSet rs = statmt.executeQuery(sqlrs);
                Player p = Sponge.getServer().getPlayer(event.player.getName()).get();
                if (rs.next()) {
                    final UUID uuid = p.getUniqueId();
                    final String g1 = Yogurtlugorev.gg.get(uuid).get("gorev1durum:");
                    final String g2 = Yogurtlugorev.gg.get(uuid).get("gorev2durum:");
                    if (rs.getString("balikci").equals("g1devam") || rs.getString("balikci").equals("g2devam") || rs.getString("balikci").equals("g3devam") || rs.getString("balikci").equals("g4devam")) {
                        if (rs.getString("gorev1").equals("balikci")) {
                            if (rs.getString("gorev1").equals("balikci")) {
                                Yogurtlugorev.durum = rs.getInt("gorev1durum");
                                statmt = connect.createStatement();
                                String sql = "UPDATE Gorev SET gorev1durum=? WHERE oyuncu='" + p.getName() + "'";
                                PreparedStatement ps = connect.prepareStatement(sql);
                                ps.setInt(1, Integer.valueOf(g1) + 1);
                                ps.executeUpdate();
                                Yogurtlugorev.gg.get(uuid).put("gorev1durum:", String.valueOf(Integer.valueOf(g1) + 1));
                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g1) + 1)));
                            }
                            if (rs.getString("balikci").equals("g1devam") && (Integer.valueOf(g1) + 1) == 5) {
                                statmt = connect.createStatement();
                                String sql2 = "UPDATE Gorev SET  balikci=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                ps2.setString(1, "g1bitti");
                                ps2.executeUpdate();
                                Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 500");
                                //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Acemi Balikci gorevini bitirdiniz, tebrikler."));
                            } else {
                                if (rs.getString("balikci").equals("g2devam") && (Integer.valueOf(g1) + 1) == 15) {
                                    statmt = connect.createStatement();
                                    String sql2 = "UPDATE Gorev SET  balikci=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                    ps2.setString(1, "g2bitti");
                                    ps2.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 1000");
                                    //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Tecrubeli Balikci gorevini bitirdiniz, tebrikler."));
                                } else {
                                    if (rs.getString("balikci").equals("g3devam") && (Integer.valueOf(g1) + 1) == 20) {
                                        statmt = connect.createStatement();
                                        String sql2 = "UPDATE Gorev SET  balikci=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                        ps2.setString(1, "g3bitti");
                                        ps2.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 2000");
                                        //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bilgili Balikci gorevini bitirdiniz, tebrikler."));
                                    } else {
                                        if (rs.getString("balikci").equals("g4devam") && (Integer.valueOf(g1) + 1) == 50) {
                                            statmt = connect.createStatement();
                                            String sql2 = "UPDATE Gorev SET  balikci=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + p.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "g4bitti");
                                            ps2.executeUpdate();
                                            Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 3000");
                                            //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                            //-------------------
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Usta Balikci gorevini bitirdiniz, tebrikler."));
                                            Text ytext = Text.builder("[").color(TextColors.AQUA).append(
                                                    Text.builder("kadayifQuest").color(TextColors.LIGHT_PURPLE).build()).append(
                                                    Text.builder("] ").color(TextColors.AQUA).build()).append(
                                                    Text.builder(p.getName()).color(TextColors.BLUE).build()).append(
                                                    Text.builder(" isimli oyuncu Balikci gorevini tamamladi. -> ").color(TextColors.GREEN).build()).append(
                                                    Text.builder("/quest").color(TextColors.BLUE).onHover(TextActions.showText(Text.of(TextColors.LIGHT_PURPLE, "Komutunu kullanarak gorevlere erisebilirsiniz."))).build()).append().append().build();
                                            Sponge.getServer().getBroadcastChannel().send(ytext);
                                        }
                                    }
                                }
                            }
                        } else {
                            if (rs.getString("gorev2").equals("balikci")) {
                                if (rs.getString("gorev2").equals("balikci")) {
                                    Yogurtlugorev.durum = rs.getInt("gorev2durum");
                                    statmt = connect.createStatement();
                                    String sql = "UPDATE Gorev SET gorev2durum=? WHERE oyuncu='" + p.getName() + "'";
                                    PreparedStatement ps = connect.prepareStatement(sql);
                                    ps.setInt(1, Integer.valueOf(g2) + 1);
                                    ps.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev2durum:", String.valueOf(Integer.valueOf(g2) + 1));
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g2) + 1)));
                                }
                                if (rs.getString("balikci").equals("g1devam") && (Integer.valueOf(g2) + 1) == 5) {
                                    statmt = connect.createStatement();
                                    String sql2 = "UPDATE Gorev SET  balikci=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                    ps2.setString(1, "g1bitti");
                                    ps2.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 500");
                                    //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Acemi Balikci gorevini bitirdiniz, tebrikler."));
                                } else {
                                    if (rs.getString("balikci").equals("g2devam") && (Integer.valueOf(g2) + 1) == 15) {
                                        statmt = connect.createStatement();
                                        String sql2 = "UPDATE Gorev SET  balikci=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                        ps2.setString(1, "g2bitti");
                                        ps2.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 1000");
                                        //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Tecrubeli Balikci gorevini bitirdiniz, tebrikler."));
                                    } else {
                                        if (rs.getString("balikci").equals("g3devam") && (Integer.valueOf(g2) + 1) == 20) {
                                            statmt = connect.createStatement();
                                            String sql2 = "UPDATE Gorev SET  balikci=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "g3bitti");
                                            ps2.executeUpdate();
                                            Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 2000");
                                            //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bilgili Balikci gorevini bitirdiniz, tebrikler."));
                                        } else {
                                            if (rs.getString("balikci").equals("g4devam") && (Integer.valueOf(g2) + 1) == 50) {
                                                statmt = connect.createStatement();
                                                String sql2 = "UPDATE Gorev SET  balikci=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + p.getName() + "'";
                                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                ps2.setString(1, "g4bitti");
                                                ps2.executeUpdate();
                                                Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 3000");
                                                //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                                //-------------------------
                                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Usta Balikci gorevini bitirdiniz, tebrikler."));
                                                Text ytext = Text.builder("[").color(TextColors.AQUA).append(
                                                        Text.builder("kadayifQuest").color(TextColors.LIGHT_PURPLE).build()).append(
                                                        Text.builder("] ").color(TextColors.AQUA).build()).append(
                                                        Text.builder(p.getName()).color(TextColors.BLUE).build()).append(
                                                        Text.builder(" isimli oyuncu Balikci gorevini tamamladi. -> ").color(TextColors.GREEN).build()).append(
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
