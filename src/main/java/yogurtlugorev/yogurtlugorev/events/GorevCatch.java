package yogurtlugorev.yogurtlugorev.events;

import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import yogurtlugorev.yogurtlugorev.Yogurtlugorev;

import java.sql.*;
import java.util.UUID;

public class GorevCatch {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./kadayifdb/kadayifquest";
    static final String USER = "sa";
    static final String PASS = "";
    Connection connect = null;
    Statement statmt = null;
    Statement statmt2 = null;

    @SubscribeEvent
    public void onCatch(CaptureEvent.SuccessfulCapture event) {
        try {
            connect = DriverManager.getConnection(DB_URL, USER, PASS);
            statmt = connect.createStatement();
            try {
                statmt = connect.createStatement();
                String sqlrs = "SELECT * FROM Gorev WHERE oyuncu='" + event.player.getName() + "'";
                ResultSet rs = statmt.executeQuery(sqlrs);
                Player p = (Player) event.player;
                if (rs.next()) {
                    final UUID uuid = p.getUniqueId();
                    final String g1 = Yogurtlugorev.gg.get(uuid).get("gorev1durum:");
                    final String g2 = Yogurtlugorev.gg.get(uuid).get("gorev2durum:");
                    if (rs.getString("pokeavcisi").equals("g1devam") || rs.getString("pokeavcisi").equals("g2devam") || rs.getString("pokeavcisi").equals("g3devam") || rs.getString("pokeavcisi").equals("g4devam")) {
                        if (rs.getString("gorev1").equals("pokeavcisi")) {
                            if (rs.getString("gorev1").equals("pokeavcisi")) {
                                Yogurtlugorev.durum = rs.getInt("gorev1durum");
                                statmt = connect.createStatement();
                                String sql = "UPDATE Gorev SET gorev1durum=? WHERE oyuncu='" + event.player.getName() + "'";
                                PreparedStatement ps = connect.prepareStatement(sql);
                                ps.setInt(1, Integer.valueOf(g1) + 1);
                                ps.executeUpdate();
                                Yogurtlugorev.gg.get(uuid).put("gorev1durum:", String.valueOf(Integer.valueOf(g1) + 1));
                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g1) + 1)));
                            }
                            if (rs.getString("pokeavcisi").equals("g1devam") && (Integer.valueOf(g1) + 1) == 20) {
                                statmt = connect.createStatement();
                                String sql2 = "UPDATE Gorev SET  pokeavcisi=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                ps2.setString(1, "g1bitti");
                                ps2.executeUpdate();
                                Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 500");
                                //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Acemi Avci gorevini bitirdiniz, tebrikler."));
                            } else {
                                if (rs.getString("pokeavcisi").equals("g2devam") && (Integer.valueOf(g1) + 1) == 50) {
                                    statmt = connect.createStatement();
                                    String sql2 = "UPDATE Gorev SET  pokeavcisi=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                    ps2.setString(1, "g2bitti");
                                    ps2.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 1000");
                                    //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Tecrubeli Avci gorevini bitirdiniz, tebrikler."));
                                } else {
                                    if (rs.getString("pokeavcisi").equals("g3devam") && (Integer.valueOf(g1) + 1) == 75) {
                                        statmt = connect.createStatement();
                                        String sql2 = "UPDATE Gorev SET  pokeavcisi=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                        ps2.setString(1, "g3bitti");
                                        ps2.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 2000");
                                        //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bilgili Avci gorevini bitirdiniz, tebrikler."));
                                    } else {
                                        if (rs.getString("pokeavcisi").equals("g4devam") && (Integer.valueOf(g1) + 1) == 150) {
                                            statmt = connect.createStatement();
                                            String sql2 = "UPDATE Gorev SET  pokeavcisi=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "g4bitti");
                                            ps2.executeUpdate();
                                            Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 3000");
                                            //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
//---------
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Usta Avci gorevini bitirdiniz, tebrikler."));
                                            Text ytext = Text.builder("[").color(TextColors.AQUA).append(
                                                    Text.builder("kadayifQuest").color(TextColors.LIGHT_PURPLE).build()).append(
                                                    Text.builder("] ").color(TextColors.AQUA).build()).append(
                                                    Text.builder(p.getName()).color(TextColors.BLUE).build()).append(
                                                    Text.builder(" isimli oyuncu Pokemon Avcisi gorevini tamamladi. -> ").color(TextColors.GREEN).build()).append(
                                                    Text.builder("/quest").color(TextColors.BLUE).onHover(TextActions.showText(Text.of(TextColors.LIGHT_PURPLE, "Komutunu kullanarak gorevlere erisebilirsiniz."))).build()).append().append().build();
                                            Sponge.getServer().getBroadcastChannel().send(ytext);
                                        }
                                    }
                                }
                            }
                        } else {
                            if (rs.getString("gorev2").equals("pokeavcisi")) {
                                if (rs.getString("gorev2").equals("pokeavcisi")) {
                                    Yogurtlugorev.durum = rs.getInt("gorev2durum");
                                    statmt = connect.createStatement();
                                    String sql = "UPDATE Gorev SET gorev2durum=? WHERE oyuncu='" + event.player.getName() + "'";
                                    PreparedStatement ps = connect.prepareStatement(sql);
                                    ps.setInt(1, Integer.valueOf(g2) + 1);
                                    ps.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev2durum:", String.valueOf(Integer.valueOf(g2) + 1));
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g2) + 1)));
                                }
                                if (rs.getString("pokeavcisi").equals("g1devam") && (Integer.valueOf(g2) + 1) == 20) {
                                    statmt = connect.createStatement();
                                    String sql2 = "UPDATE Gorev SET  pokeavcisi=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                    ps2.setString(1, "g1bitti");
                                    ps2.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 500");
                                    //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Acemi Avci gorevini bitirdiniz, tebrikler."));
                                } else {
                                    if (rs.getString("pokeavcisi").equals("g2devam") && (Integer.valueOf(g2) + 1) == 50) {
                                        statmt = connect.createStatement();
                                        String sql2 = "UPDATE Gorev SET  pokeavcisi=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                        ps2.setString(1, "g2bitti");
                                        ps2.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 1000");
                                        //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Tecrubeli Avci gorevini bitirdiniz, tebrikler."));
                                    } else {
                                        if (rs.getString("pokeavcisi").equals("g3devam") && (Integer.valueOf(g2) + 1) == 75) {
                                            statmt = connect.createStatement();
                                            String sql2 = "UPDATE Gorev SET  pokeavcisi=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "g3bitti");
                                            ps2.executeUpdate();
                                            Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 2000");
                                            //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bilgili Avci gorevini bitirdiniz, tebrikler."));
                                        } else {
                                            if (rs.getString("pokeavcisi").equals("g4devam") && (Integer.valueOf(g2) + 1) == 150) {
                                                statmt = connect.createStatement();
                                                String sql2 = "UPDATE Gorev SET  pokeavcisi=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                ps2.setString(1, "g4bitti");
                                                ps2.executeUpdate();
                                                Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 3000");
                                                //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                                //--------------------
                                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Usta Avci gorevini bitirdiniz, tebrikler."));
                                                Text ytext = Text.builder("[").color(TextColors.AQUA).append(
                                                        Text.builder("kadayifQuest").color(TextColors.LIGHT_PURPLE).build()).append(
                                                        Text.builder("] ").color(TextColors.AQUA).build()).append(
                                                        Text.builder(p.getName()).color(TextColors.BLUE).build()).append(
                                                        Text.builder(" isimli oyuncu Pokemon Avcisi gorevini tamamladi. -> ").color(TextColors.GREEN).build()).append(
                                                        Text.builder("/quest").color(TextColors.BLUE).onHover(TextActions.showText(Text.of(TextColors.LIGHT_PURPLE, "Komutunu kullanarak gorevlere erisebilirsiniz."))).build()).append().append().build();
                                                Sponge.getServer().getBroadcastChannel().send(ytext);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (rs.getString("shinyavcisi").equals("g1devam") || rs.getString("shinyavcisi").equals("g2devam") || rs.getString("shinyavcisi").equals("g3devam") || rs.getString("shinyavcisi").equals("g4devam")) {
                        if (rs.getString("gorev1").equals("shinyavcisi") && event.getPokemon().getPokemonData().isShiny()) {
                            if (rs.getString("gorev1").equals("shinyavcisi")) {
                                Yogurtlugorev.durum = rs.getInt("gorev1durum");
                                statmt = connect.createStatement();
                                String sql = "UPDATE Gorev SET gorev1durum=? WHERE oyuncu='" + event.player.getName() + "'";
                                PreparedStatement ps = connect.prepareStatement(sql);
                                ps.setInt(1, Integer.valueOf(g1) + 1);
                                ps.executeUpdate();
                                Yogurtlugorev.gg.get(uuid).put("gorev1durum:", String.valueOf(Integer.valueOf(g1) + 1));
                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g1) + 1)));
                            }
                            if (rs.getString("shinyavcisi").equals("g1devam") && (Integer.valueOf(g1) + 1) == 5) {
                                statmt = connect.createStatement();
                                String sql2 = "UPDATE Gorev SET  shinyavcisi=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                ps2.setString(1, "g1bitti");
                                ps2.executeUpdate();
                                Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 500");
                                //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Acemi Shiny Avcisi gorevini bitirdiniz, tebrikler."));
                            } else {
                                if (rs.getString("shinyavcisi").equals("g2devam") && (Integer.valueOf(g1) + 1) == 10) {
                                    statmt = connect.createStatement();
                                    String sql2 = "UPDATE Gorev SET  shinyavcisi=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                    ps2.setString(1, "g2bitti");
                                    ps2.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 1000");
                                    //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Tecrubeli Shiny Avcisi gorevini bitirdiniz, tebrikler."));
                                } else {
                                    if (rs.getString("shinyavcisi").equals("g3devam") && (Integer.valueOf(g1) + 1) == 25) {
                                        statmt = connect.createStatement();
                                        String sql2 = "UPDATE Gorev SET  shinyavcisi=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                        ps2.setString(1, "g3bitti");
                                        ps2.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 2000");
                                        //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bilgili Shiny Avcisi gorevini bitirdiniz, tebrikler."));
                                    } else {
                                        if (rs.getString("shinyavcisi").equals("g4devam") && (Integer.valueOf(g1) + 1) == 75) {
                                            statmt = connect.createStatement();
                                            String sql2 = "UPDATE Gorev SET  shinyavcisi=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "g4bitti");
                                            ps2.executeUpdate();
                                            Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 3000");
                                            //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                            //-----------------------
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Usta Shiny Avcisi gorevini bitirdiniz, tebrikler."));
                                            Text ytext = Text.builder("[").color(TextColors.AQUA).append(
                                                    Text.builder("kadayifQuest").color(TextColors.LIGHT_PURPLE).build()).append(
                                                    Text.builder("] ").color(TextColors.AQUA).build()).append(
                                                    Text.builder(p.getName()).color(TextColors.BLUE).build()).append(
                                                    Text.builder(" isimli oyuncu Shiny Avcisi gorevini tamamladi. -> ").color(TextColors.GREEN).build()).append(
                                                    Text.builder("/quest").color(TextColors.BLUE).onHover(TextActions.showText(Text.of(TextColors.LIGHT_PURPLE, "Komutunu kullanarak gorevlere erisebilirsiniz."))).build()).append().append().build();
                                            Sponge.getServer().getBroadcastChannel().send(ytext);
                                        }
                                    }
                                }
                            }
                        } else {
                            if (rs.getString("gorev2").equals("shinyavcisi") && event.getPokemon().getPokemonData().isShiny()) {
                                if (rs.getString("gorev2").equals("shinyavcisi")) {
                                    Yogurtlugorev.durum = rs.getInt("gorev2durum");
                                    statmt = connect.createStatement();
                                    String sql = "UPDATE Gorev SET gorev2durum=? WHERE oyuncu='" + event.player.getName() + "'";
                                    PreparedStatement ps = connect.prepareStatement(sql);
                                    ps.setInt(1, Integer.valueOf(g2) + 1);
                                    ps.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev2durum:", String.valueOf(Integer.valueOf(g2) + 1));
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g2) + 1)));
                                }
                                if (rs.getString("shinyavcisi").equals("g1devam") && (Integer.valueOf(g2) + 1) == 5) {
                                    statmt = connect.createStatement();
                                    String sql2 = "UPDATE Gorev SET  shinyavcisi=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                    ps2.setString(1, "g1bitti");
                                    ps2.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 500");
                                    //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Acemi Shiny Avcisi gorevini bitirdiniz, tebrikler."));
                                } else {
                                    if (rs.getString("shinyavcisi").equals("g2devam") && (Integer.valueOf(g2) + 1) == 10) {
                                        statmt = connect.createStatement();
                                        String sql2 = "UPDATE Gorev SET  shinyavcisi=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                        ps2.setString(1, "g2bitti");
                                        ps2.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 1000");
                                        //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Tecrubeli Shiny Avcisi gorevini bitirdiniz, tebrikler."));
                                    } else {
                                        if (rs.getString("shinyavcisi").equals("g3devam") && (Integer.valueOf(g2) + 1) == 25) {
                                            statmt = connect.createStatement();
                                            String sql2 = "UPDATE Gorev SET  shinyavcisi=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "g3bitti");
                                            ps2.executeUpdate();
                                            Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 2000");
                                            //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bilgili Shiny Avcisi gorevini bitirdiniz, tebrikler."));
                                        } else {
                                            if (rs.getString("shinyavcisi").equals("g4devam") && (Integer.valueOf(g2) + 1) == 75) {
                                                statmt = connect.createStatement();
                                                String sql2 = "UPDATE Gorev SET  shinyavcisi=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                ps2.setString(1, "g4bitti");
                                                ps2.executeUpdate();
                                                Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 3000");
                                                //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                                //-------------------------
                                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Usta Shiny Avcisi gorevini bitirdiniz, tebrikler."));
                                                Text ytext = Text.builder("[").color(TextColors.AQUA).append(
                                                        Text.builder("kadayifQuest").color(TextColors.LIGHT_PURPLE).build()).append(
                                                        Text.builder("] ").color(TextColors.AQUA).build()).append(
                                                        Text.builder(p.getName()).color(TextColors.BLUE).build()).append(
                                                        Text.builder(" isimli oyuncu Shiny Avcisi gorevini tamamladi. -> ").color(TextColors.GREEN).build()).append(
                                                        Text.builder("/quest").color(TextColors.BLUE).onHover(TextActions.showText(Text.of(TextColors.LIGHT_PURPLE, "Komutunu kullanarak gorevlere erisebilirsiniz."))).build()).append().append().build();
                                                Sponge.getServer().getBroadcastChannel().send(ytext);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (rs.getString("legavcisi").equals("g1devam") || rs.getString("legavcisi").equals("g2devam") || rs.getString("legavcisi").equals("g3devam") || rs.getString("legavcisi").equals("g4devam")) {
                        if (rs.getString("gorev1").equals("legavcisi") && Yogurtlugorev.LegList.contains(event.getPokemon().getName() + ",")) {
                            if (rs.getString("gorev1").equals("legavcisi")) {
                                Yogurtlugorev.durum = rs.getInt("gorev1durum");
                                statmt = connect.createStatement();
                                String sql = "UPDATE Gorev SET gorev1durum=? WHERE oyuncu='" + event.player.getName() + "'";
                                PreparedStatement ps = connect.prepareStatement(sql);
                                ps.setInt(1, Integer.valueOf(g1) + 1);
                                ps.executeUpdate();
                                Yogurtlugorev.gg.get(uuid).put("gorev1durum:", String.valueOf(Integer.valueOf(g1) + 1));
                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g1) + 1)));
                            }
                            if (rs.getString("legavcisi").equals("g1devam") && (Integer.valueOf(g1) + 1) == 1) {
                                statmt = connect.createStatement();
                                String sql2 = "UPDATE Gorev SET  legavcisi=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                ps2.setString(1, "g1bitti");
                                ps2.executeUpdate();
                                Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 500");
                                //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Acemi Leg Avcisi gorevini bitirdiniz, tebrikler."));
                            } else {
                                if (rs.getString("legavcisi").equals("g2devam") && (Integer.valueOf(g1) + 1) == 3) {
                                    statmt = connect.createStatement();
                                    String sql2 = "UPDATE Gorev SET  legavcisi=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                    ps2.setString(1, "g2bitti");
                                    ps2.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 1000");
                                    //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Tecrubeli Leg Avcisi gorevini bitirdiniz, tebrikler."));
                                } else {
                                    if (rs.getString("legavcisi").equals("g3devam") && (Integer.valueOf(g1) + 1) == 5) {
                                        statmt = connect.createStatement();
                                        String sql2 = "UPDATE Gorev SET  legavcisi=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                        ps2.setString(1, "g3bitti");
                                        ps2.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 2000");
                                        //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bilgili Leg Avcisi gorevini bitirdiniz, tebrikler."));
                                    } else {
                                        if (rs.getString("legavcisi").equals("g4devam") && (Integer.valueOf(g1) + 1) == 10) {
                                            statmt = connect.createStatement();
                                            String sql2 = "UPDATE Gorev SET  legavcisi=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "g4bitti");
                                            ps2.executeUpdate();
                                            Yogurtlugorev.gg.get(uuid).put("gorev1durum:", "0");
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 3000");
                                            //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                            //------------------
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Usta Leg Avcisi gorevini bitirdiniz, tebrikler."));
                                            Text ytext = Text.builder("[").color(TextColors.AQUA).append(
                                                    Text.builder("kadayifQuest").color(TextColors.LIGHT_PURPLE).build()).append(
                                                    Text.builder("] ").color(TextColors.AQUA).build()).append(
                                                    Text.builder(p.getName()).color(TextColors.BLUE).build()).append(
                                                    Text.builder(" isimli oyuncu Leg Avcisi gorevini tamamladi. -> ").color(TextColors.GREEN).build()).append(
                                                    Text.builder("/quest").color(TextColors.BLUE).onHover(TextActions.showText(Text.of(TextColors.LIGHT_PURPLE, "Komutunu kullanarak gorevlere erisebilirsiniz."))).build()).append().append().build();
                                            Sponge.getServer().getBroadcastChannel().send(ytext);
                                        }
                                    }
                                }
                            }
                        } else {
                            if (rs.getString("gorev2").equals("legavcisi") && Yogurtlugorev.LegList.contains(event.getPokemon().getName() + ",")) {
                                if (rs.getString("gorev2").equals("legavcisi")) {
                                    Yogurtlugorev.durum = rs.getInt("gorev2durum");
                                    statmt = connect.createStatement();
                                    String sql = "UPDATE Gorev SET gorev2durum=? WHERE oyuncu='" + event.player.getName() + "'";
                                    PreparedStatement ps = connect.prepareStatement(sql);
                                    ps.setInt(1, Integer.valueOf(g2) + 1);
                                    ps.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev2durum:", String.valueOf(Integer.valueOf(g2) + 1));
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorevinizde ilerlediniz, ilerlemeniz: " + (Integer.valueOf(g2) + 1)));
                                }
                                if (rs.getString("legavcisi").equals("g1devam") && (Integer.valueOf(g2) + 1) == 1) {
                                    statmt = connect.createStatement();
                                    String sql2 = "UPDATE Gorev SET  legavcisi=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                    ps2.setString(1, "g1bitti");
                                    ps2.executeUpdate();
                                    Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 500");
                                    //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Acemi Leg Avcisi gorevini bitirdiniz, tebrikler."));
                                } else {
                                    if (rs.getString("legavcisi").equals("g2devam") && (Integer.valueOf(g2) + 1) == 3) {
                                        statmt = connect.createStatement();
                                        String sql2 = "UPDATE Gorev SET  legavcisi=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                        ps2.setString(1, "g2bitti");
                                        ps2.executeUpdate();
                                        Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                        Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 1000");
                                        //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 1");
                                        p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Tecrubeli Leg Avcisi gorevini bitirdiniz, tebrikler."));
                                    } else {
                                        if (rs.getString("legavcisi").equals("g3devam") && (Integer.valueOf(g2) + 1) == 5) {
                                            statmt = connect.createStatement();
                                            String sql2 = "UPDATE Gorev SET  legavcisi=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "g3bitti");
                                            ps2.executeUpdate();
                                            Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 2000");
                                            //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                            p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bilgili Leg Avcisi gorevini bitirdiniz, tebrikler."));
                                        } else {
                                            if (rs.getString("legavcisi").equals("g4devam") && (Integer.valueOf(g2) + 1) == 10) {
                                                statmt = connect.createStatement();
                                                String sql2 = "UPDATE Gorev SET  legavcisi=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + event.player.getName() + "'";
                                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                ps2.setString(1, "g4bitti");
                                                ps2.executeUpdate();
                                                Yogurtlugorev.gg.get(uuid).put("gorev2durum:", "0");
                                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 3000");
                                                //Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "crates give key ucuzkasa " + p.getName() + " 2");
                                                //----------------------
                                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Usta Leg Avcisi gorevini bitirdiniz, tebrikler."));
                                                Text ytext = Text.builder("[").color(TextColors.AQUA).append(
                                                        Text.builder("kadayifQuest").color(TextColors.LIGHT_PURPLE).build()).append(
                                                        Text.builder("] ").color(TextColors.AQUA).build()).append(
                                                        Text.builder(p.getName()).color(TextColors.BLUE).build()).append(
                                                        Text.builder(" isimli oyuncu Leg Avcisi gorevini tamamladi. -> ").color(TextColors.GREEN).build()).append(
                                                        Text.builder("/quest").color(TextColors.BLUE).onHover(TextActions.showText(Text.of(TextColors.LIGHT_PURPLE, "Komutunu kullanarak gorevlere erisebilirsiniz."))).build()).append().append().build();
                                                Sponge.getServer().getBroadcastChannel().send(ytext);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }


                    //-------------------------------------------------- Günlük --------------------------------------------------
                    statmt = connect.createStatement();
                    String sqlrs2 = "SELECT * FROM GunlukGorev WHERE id='0'";
                    ResultSet rs2 = statmt.executeQuery(sqlrs2);
                    if (rs2.next()) {
                        if (rs2.getInt("durum") == 1 && Yogurtlugorev.gg.get(uuid) != null) {
                            final String value = Yogurtlugorev.gg.get(uuid).get("gunlukgorev:");
                            if (value.equals("0")) {
                                if (rs2.getString("gorev").equals("pokeavla")) {
                                    String gorevpokemon = rs2.getString("gorevpokemon");
                                    Integer totalivs = Integer.valueOf(rs2.getString("gorevtotalivs"));
                                    if (PokemonSpec.from(gorevpokemon).name != null) {
                                        if (event.getPokemon().getName().toLowerCase().equals(gorevpokemon.toLowerCase())) {
                                            if (totalivs >= 50 && event.getPokemon().getPokemonData().getIVs().getPercentage(1) >= totalivs) {
                                                Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 20000");
                                                p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, " Gunluk gorevi yaptiniz, tebrikler."));
                                                Yogurtlugorev.gg.get(uuid).put("gunlukgorev:", "1");
                                            } else {
                                                if (totalivs < 50 && event.getPokemon().getPokemonData().getIVs().getPercentage(1) <= totalivs) {
                                                    Sponge.getCommandManager().process(Sponge.getServer().getConsole(), "givemoney " + p.getName() + " 20000");
                                                    p.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, " Gunluk gorevi yaptiniz, tebrikler."));
                                                    Yogurtlugorev.gg.get(uuid).put("gunlukgorev:", "1");
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
}
