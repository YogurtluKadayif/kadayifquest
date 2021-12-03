package yogurtlugorev.yogurtlugorev;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.pixelmonmod.pixelmon.Pixelmon;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.First;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import yogurtlugorev.yogurtlugorev.events.*;
import yogurtlugorev.yogurtlugorev.guis.GuiPokedex2;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.nio.file.Files;
import java.sql.*;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Plugin(
        id = Yogurtlugorev.MOD_ID,
        name = Yogurtlugorev.MOD_NAME,
        description = "YogurtluKadayif Sponge Quest Plugin",
        authors = {
                "YogurtluKadayif"
        }
)
public class Yogurtlugorev {

    public static final String MOD_ID = "yogurtlugorev";
    public static final String MOD_NAME = "Yogurtlugorev";
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL3 = "jdbc:h2:./kadayifdb/kadayifquest";
    //public static String pokeList = "bulbasaur, ivysaur, venusaur, charmander, charmeleon, charizard, squirtle, wartortle, blastoise, caterpie, metapod, butterfree, weedle, kakuna, beedrill, pidgey, pidgeotto, pidgeot, rattata, raticate, spearow, fearow, ekans, arbok, pikachu, raichu, sandshrew, sandslash, nidoran♀, nidorina, nidoqueen, nidoran♂, nidorino, nidoking, clefairy, clefable, vulpix, ninetales, jigglypuff, wigglytuff, zubat, golbat, oddish, gloom, vileplume, paras, parasect, venonat, venomoth, diglett, dugtrio, meowth, persian, psyduck, golduck, mankey, primeape, growlithe, arcanine, poliwag, poliwhirl, poliwrath, abra, kadabra, alakazam, machop, machoke, machamp, bellsprout, weepinbell, victreebel, tentacool, tentacruel, geodude, graveler, golem, ponyta, rapidash, slowpoke, slowbro, magnemite, magneton, farfetch’d, doduo, dodrio, seel, dewgong, grimer, muk, shellder, cloyster, gastly, haunter, gengar, onix, drowzee, hypno, krabby, kingler, voltorb, electrode, exeggcute, exeggutor, cubone, marowak, hitmonlee, hitmonchan, lickitung, koffing, weezing, rhyhorn, rhydon, chansey, tangela, kangaskhan, horsea, seadra, goldeen, seaking, staryu, starmie, mr. mime, scyther, jynx, electabuzz, magmar, pinsir, tauros, magikarp, gyarados, lapras, ditto, eevee, vaporeon, jolteon, flareon, porygon, omanyte, omastar, kabuto, kabutops, aerodactyl, snorlax, articuno, zapdos, moltres, dratini, dragonair, dragonite, mewtwo, mew, chikorita, bayleef, meganium, cyndaquil, quilava, typhlosion, totodile, croconaw, feraligatr, sentret, furret, hoothoot, noctowl, ledyba, ledian, spinarak, ariados, crobat, chinchou, lanturn, pichu, cleffa, igglybuff, togepi, togetic, natu, xatu, mareep, flaaffy, ampharos, bellossom, marill, azumarill, sudowoodo, politoed, hoppip, skiploom, jumpluff, aipom, sunkern, sunflora, yanma, wooper, quagsire, espeon, umbreon, murkrow, slowking, misdreavus, unown, wobbuffet, girafarig, pineco, forretress, dunsparce, gligar, steelix, snubbull, granbull, qwilfish, scizor, shuckle, heracross, sneasel, teddiursa, ursaring, slugma, magcargo, swinub, piloswine, corsola, remoraid, octillery, delibird, mantine, skarmory, houndour, houndoom, kingdra, phanpy, donphan, porygon2, stantler, smeargle, tyrogue, hitmontop, smoochum, elekid, magby, miltank, blissey, raikou, entei, suicune, larvitar, pupitar, tyranitar, lugia, ho-oh, celebi, treecko, grovyle, sceptile, torchic, combusken, blaziken, mudkip, marshtomp, swampert, poochyena, mightyena, zigzagoon, linoone, wurmple, silcoon, beautifly, cascoon, dustox, lotad, lombre, ludicolo, seedot, nuzleaf, shiftry, taillow, swellow, wingull, pelipper, ralts, kirlia, gardevoir, surskit, masquerain, shroomish, breloom, slakoth, vigoroth, slaking, nincada, ninjask, shedinja, whismur, loudred, exploud, makuhita, hariyama, azurill, nosepass, skitty, delcatty, sableye, mawile, aron, lairon, aggron, meditite, medicham, electrike, manectric, plusle, minun, volbeat, illumise, roselia, gulpin, swalot, carvanha, sharpedo, wailmer, wailord, numel, camerupt, torkoal, spoink, grumpig, spinda, trapinch, vibrava, flygon, cacnea, cacturne, swablu, altaria, zangoose, seviper, lunatone, solrock, barboach, whiscash, corphish, crawdaunt, baltoy, claydol, lileep, cradily, anorith, armaldo, feebas, milotic, castform, kecleon, shuppet, banette, duskull, dusclops, tropius, chimecho, absol, wynaut, snorunt, glalie, spheal, sealeo, walrein, clamperl, huntail, gorebyss, relicanth, luvdisc, bagon, shelgon, salamence, beldum, metang, metagross, regirock, regice, registeel, latias, latios, kyogre, groudon, rayquaza, jirachi, deoxys, turtwig, grotle, torterra, chimchar, monferno, infernape, piplup, prinplup, empoleon, starly, staravia, staraptor, bidoof, bibarel, kricketot, kricketune, shinx, luxio, luxray, budew, roserade, cranidos, rampardos, shieldon, bastiodon, burmy, wormadam, mothim, combee, vespiquen, pachirisu, buizel, floatzel, cherubi, cherrim, shellos, gastrodon, ambipom, drifloon, drifblim, buneary, lopunny, mismagius, honchkrow, glameow, purugly, chingling, stunky, skuntank, bronzor, bronzong, bonsly, mime jr., happiny, chatot, spiritomb, gible, gabite, garchomp, munchlax, riolu, lucario, hippopotas, hippowdon, skorupi, drapion, croagunk, toxicroak, carnivine, finneon, lumineon, mantyke, snover, abomasnow, weavile, magnezone, lickilicky, rhyperior, tangrowth, electivire, magmortar, togekiss, yanmega, leafeon, glaceon, gliscor, mamoswine, porygon-z, gallade, probopass, dusknoir, froslass, rotom, uxie, mesprit, azelf, dialga, palkia, heatran, regigigas, giratina, cresselia, phione, manaphy, darkrai, shaymin, arceus, victini, snivy, servine, serperior, tepig, pignite, emboar, oshawott, dewott, samurott, patrat, watchog, lillipup, herdier, stoutland, purrloin, liepard, pansage, simisage, pansear, simisear, panpour, simipour, munna, musharna, pidove, tranquill, unfezant, blitzle, zebstrika, roggenrola, boldore, gigalith, woobat, swoobat, drilbur, excadrill, audino, timburr, gurdurr, conkeldurr, tympole, palpitoad, seismitoad, throh, sawk, sewaddle, swadloon, leavanny, venipede, whirlipede, scolipede, cottonee, whimsicott, petilil, lilligant, basculin, sandile, krokorok, krookodile, darumaka, darmanitan, maractus, dwebble, crustle, scraggy, scrafty, sigilyph, yamask, cofagrigus, tirtouga, carracosta, archen, archeops, trubbish, garbodor, zorua, zoroark, minccino, cinccino, gothita, gothorita, gothitelle, solosis, duosion, reuniclus, ducklett, swanna, vanillite, vanillish, vanilluxe, deerling, sawsbuck, emolga, karrablast, escavalier, foongus, amoonguss, frillish, jellicent, alomomola, joltik, galvantula, ferroseed, ferrothorn, klink, klang, klinklang, tynamo, eelektrik, eelektross, elgyem, beheeyem, litwick, lampent, chandelure, axew, fraxure, haxorus, cubchoo, beartic, cryogonal, shelmet, accelgor, stunfisk, mienfoo, mienshao, druddigon, golett, golurk, pawniard, bisharp, bouffalant, rufflet, braviary, vullaby, mandibuzz, heatmor, durant, deino, zweilous, hydreigon, larvesta, volcarona, cobalion, terrakion, virizion, tornadus, thundurus, reshiram, zekrom, landorus, kyurem, keldeo, meloetta, genesect, chespin, quilladin, chesnaught, fennekin, braixen, delphox, froakie, frogadier, greninja, bunnelby, diggersby, fletchling, fletchinder, talonflame, scatterbug, spewpa, vivillon, litleo, pyroar, flabébé, floette, florges, skiddo, gogoat, pancham, pangoro, furfrou, espurr, meowstic, honedge, doublade, aegislash, spritzee, aromatisse, swirlix, slurpuff, inkay, malamar, binacle, barbaracle, skrelp, dragalge, clauncher, clawitzer, helioptile, heliolisk, tyrunt, tyrantrum, amaura, aurorus, sylveon, hawlucha, dedenne, carbink, goomy, sliggoo, goodra, klefki, phantump, trevenant, pumpkaboo, gourgeist, bergmite, avalugg, noibat, noivern, xerneas, yveltal, zygarde, diancie, hoopa, volcanion, rowlet, dartrix, decidueye, litten, torracat, incineroar, popplio, brionne, primarina, pikipek, trumbeak, toucannon, yungoos, gumshoos, grubbin, charjabug, vikavolt, crabrawler, crabominable, oricorio, cutiefly, ribombee, rockruff, lycanroc, wishiwashi, mareanie, toxapex, mudbray, mudsdale, dewpider, araquanid, fomantis, lurantis, morelull, shiinotic, salandit, salazzle, stufful, bewear, bounsweet, steenee, tsareena, comfey, oranguru, passimian, wimpod, golisopod, sandygast, palossand, pyukumuku, type: null, silvally, minior, komala, turtonator, togedemaru, mimikyu, bruxish, drampa, dhelmise, jangmo-o, hakamo-o, kommo-o, tapu koko, tapu lele, tapu bulu, tapu fini, cosmog, cosmoem, solgaleo, lunala, nihilego, buzzwole, pheromosa, xurkitree, celesteela, kartana, guzzlord, necrozma, magearna, marshadow, poipole, naganadel, stakataka, blacephalon, zeraora, meltan, melmetal, grookey, thwackey, rillaboom, scorbunny, raboot, cinderace, sobble, drizzile, inteleon, skwovet, greedent, rookidee, corvisquire, corviknight, blipbug, dottler, orbeetle, nickit, thievul, gossifleur, eldegoss, wooloo, dubwool, chewtle, drednaw, yamper, boltund, rolycoly, carkol, coalossal, applin, flapple, appletun, silicobra, sandaconda, cramorant, arrokuda, barraskewda, toxel, toxtricity, sizzlipede, centiskorch, clobbopus, grapploct, sinistea, polteageist, hatenna, hattrem, hatterene, impidimp, morgrem, grimmsnarl, obstagoon, perrserker, cursola, sirfetch’d, mr. rime, runerigus, milcery, alcremie, falinks, pincurchin, snom, frosmoth, stonjourner, eiscue, indeedee, morpeko, cufant, copperajah, dracozolt, arctozolt, dracovish, arctovish, duraludon, dreepy, drakloak, dragapult, zacian, zamazenta, eternatus, kubfu, urshifu, zarude";
    public static String pokeList = "Bulbasaur, Ivysaur, Venusaur, Charmander, Charmeleon, Charizard, Squirtle, Wartortle, Blastoise, Caterpie, Metapod, Butterfree, Weedle, Kakuna, Beedrill, Pidgey, Pidgeotto, Pidgeot, Rattata, Raticate, Spearow, Fearow, Ekans, Arbok, Pikachu, Raichu, Sandshrew, Sandslash, Nidoran♀, Nidorina, Nidoqueen, Nidoran♂, Nidorino, Nidoking, Clefairy, Clefable, Vulpix, Ninetales, Jigglypuff, Wigglytuff, Zubat, Golbat, Oddish, Gloom, Vileplume, Paras, Parasect, Venonat, Venomoth, Diglett, Dugtrio, Meowth, Persian, Psyduck, Golduck, Mankey, Primeape, Growlithe, Arcanine, Poliwag, Poliwhirl, Poliwrath, Abra, Kadabra, Alakazam, Machop, Machoke, Machamp, Bellsprout, Weepinbell, Victreebel, Tentacool, Tentacruel, Geodude, Graveler, Golem, Ponyta, Rapidash, Slowpoke, Slowbro, Magnemite, Magneton, Farfetch’d, Doduo, Dodrio, Seel, Dewgong, Grimer, Muk, Shellder, Cloyster, Gastly, Haunter, Gengar, Onix, Drowzee, Hypno, Krabby, Kingler, Voltorb, Electrode, Exeggcute, Exeggutor, Cubone, Marowak, Hitmonlee, Hitmonchan, Lickitung, Koffing, Weezing, Rhyhorn, Rhydon, Chansey, Tangela, Kangaskhan, Horsea, Seadra, Goldeen, Seaking, Staryu, Starmie, Mr. Mime, Scyther, Jynx, Electabuzz, Magmar, Pinsir, Tauros, Magikarp, Gyarados, Lapras, Ditto, Eevee, Vaporeon, Jolteon, Flareon, Porygon, Omanyte, Omastar, Kabuto, Kabutops, Aerodactyl, Snorlax, Articuno, Zapdos, Moltres, Dratini, Dragonair, Dragonite, Mewtwo, Mew, Chikorita, Bayleef, Meganium, Cyndaquil, Quilava, Typhlosion, Totodile, Croconaw, Feraligatr, Sentret, Furret, Hoothoot, Noctowl, Ledyba, Ledian, Spinarak, Ariados, Crobat, Chinchou, Lanturn, Pichu, Cleffa, Igglybuff, Togepi, Togetic, Natu, Xatu, Mareep, Flaaffy, Ampharos, Bellossom, Marill, Azumarill, Sudowoodo, Politoed, Hoppip, Skiploom, Jumpluff, Aipom, Sunkern, Sunflora, Yanma, Wooper, Quagsire, Espeon, Umbreon, Murkrow, Slowking, Misdreavus, Unown, Wobbuffet, Girafarig, Pineco, Forretress, Dunsparce, Gligar, Steelix, Snubbull, Granbull, Qwilfish, Scizor, Shuckle, Heracross, Sneasel, Teddiursa, Ursaring, Slugma, Magcargo, Swinub, Piloswine, Corsola, Remoraid, Octillery, Delibird, Mantine, Skarmory, Houndour, Houndoom, Kingdra, Phanpy, Donphan, Porygon2, Stantler, Smeargle, Tyrogue, Hitmontop, Smoochum, Elekid, Magby, Miltank, Blissey, Raikou, Entei, Suicune, Larvitar, Pupitar, Tyranitar, Lugia, Ho-Oh, Celebi, Treecko, Grovyle, Sceptile, Torchic, Combusken, Blaziken, Mudkip, Marshtomp, Swampert, Poochyena, Mightyena, Zigzagoon, Linoone, Wurmple, Silcoon, Beautifly, Cascoon, Dustox, Lotad, Lombre, Ludicolo, Seedot, Nuzleaf, Shiftry, Taillow, Swellow, Wingull, Pelipper, Ralts, Kirlia, Gardevoir, Surskit, Masquerain, Shroomish, Breloom, Slakoth, Vigoroth, Slaking, Nincada, Ninjask, Shedinja, Whismur, Loudred, Exploud, Makuhita, Hariyama, Azurill, Nosepass, Skitty, Delcatty, Sableye, Mawile, Aron, Lairon, Aggron, Meditite, Medicham, Electrike, Manectric, Plusle, Minun, Volbeat, Illumise, Roselia, Gulpin, Swalot, Carvanha, Sharpedo, Wailmer, Wailord, Numel, Camerupt, Torkoal, Spoink, Grumpig, Spinda, Trapinch, Vibrava, Flygon, Cacnea, Cacturne, Swablu, Altaria, Zangoose, Seviper, Lunatone, Solrock, Barboach, Whiscash, Corphish, Crawdaunt, Baltoy, Claydol, Lileep, Cradily, Anorith, Armaldo, Feebas, Milotic, Castform, Kecleon, Shuppet, Banette, Duskull, Dusclops, Tropius, Chimecho, Absol, Wynaut, Snorunt, Glalie, Spheal, Sealeo, Walrein, Clamperl, Huntail, Gorebyss, Relicanth, Luvdisc, Bagon, Shelgon, Salamence, Beldum, Metang, Metagross, Regirock, Regice, Registeel, Latias, Latios, Kyogre, Groudon, Rayquaza, Jirachi, Deoxys, Turtwig, Grotle, Torterra, Chimchar, Monferno, Infernape, Piplup, Prinplup, Empoleon, Starly, Staravia, Staraptor, Bidoof, Bibarel, Kricketot, Kricketune, Shinx, Luxio, Luxray, Budew, Roserade, Cranidos, Rampardos, Shieldon, Bastiodon, Burmy, Wormadam, Mothim, Combee, Vespiquen, Pachirisu, Buizel, Floatzel, Cherubi, Cherrim, Shellos, Gastrodon, Ambipom, Drifloon, Drifblim, Buneary, Lopunny, Mismagius, Honchkrow, Glameow, Purugly, Chingling, Stunky, Skuntank, Bronzor, Bronzong, Bonsly, Mime Jr., Happiny, Chatot, Spiritomb, Gible, Gabite, Garchomp, Munchlax, Riolu, Lucario, Hippopotas, Hippowdon, Skorupi, Drapion, Croagunk, Toxicroak, Carnivine, Finneon, Lumineon, Mantyke, Snover, Abomasnow, Weavile, Magnezone, Lickilicky, Rhyperior, Tangrowth, Electivire, Magmortar, Togekiss, Yanmega, Leafeon, Glaceon, Gliscor, Mamoswine, Porygon-Z, Gallade, Probopass, Dusknoir, Froslass, Rotom, Uxie, Mesprit, Azelf, Dialga, Palkia, Heatran, Regigigas, Giratina, Cresselia, Phione, Manaphy, Darkrai, Shaymin, Arceus, Victini, Snivy, Servine, Serperior, Tepig, Pignite, Emboar, Oshawott, Dewott, Samurott, Patrat, Watchog, Lillipup, Herdier, Stoutland, Purrloin, Liepard, Pansage, Simisage, Pansear, Simisear, Panpour, Simipour, Munna, Musharna, Pidove, Tranquill, Unfezant, Blitzle, Zebstrika, Roggenrola, Boldore, Gigalith, Woobat, Swoobat, Drilbur, Excadrill, Audino, Timburr, Gurdurr, Conkeldurr, Tympole, Palpitoad, Seismitoad, Throh, Sawk, Sewaddle, Swadloon, Leavanny, Venipede, Whirlipede, Scolipede, Cottonee, Whimsicott, Petilil, Lilligant, Basculin, Sandile, Krokorok, Krookodile, Darumaka, Darmanitan, Maractus, Dwebble, Crustle, Scraggy, Scrafty, Sigilyph, Yamask, Cofagrigus, Tirtouga, Carracosta, Archen, Archeops, Trubbish, Garbodor, Zorua, Zoroark, Minccino, Cinccino, Gothita, Gothorita, Gothitelle, Solosis, Duosion, Reuniclus, Ducklett, Swanna, Vanillite, Vanillish, Vanilluxe, Deerling, Sawsbuck, Emolga, Karrablast, Escavalier, Foongus, Amoonguss, Frillish, Jellicent, Alomomola, Joltik, Galvantula, Ferroseed, Ferrothorn, Klink, Klang, Klinklang, Tynamo, Eelektrik, Eelektross, Elgyem, Beheeyem, Litwick, Lampent, Chandelure, Axew, Fraxure, Haxorus, Cubchoo, Beartic, Cryogonal, Shelmet, Accelgor, Stunfisk, Mienfoo, Mienshao, Druddigon, Golett, Golurk, Pawniard, Bisharp, Bouffalant, Rufflet, Braviary, Vullaby, Mandibuzz, Heatmor, Durant, Deino, Zweilous, Hydreigon, Larvesta, Volcarona, Cobalion, Terrakion, Virizion, Tornadus, Thundurus, Reshiram, Zekrom, Landorus, Kyurem, Keldeo, Meloetta, Genesect, Chespin, Quilladin, Chesnaught, Fennekin, Braixen, Delphox, Froakie, Frogadier, Greninja, Bunnelby, Diggersby, Fletchling, Fletchinder, Talonflame, Scatterbug, Spewpa, Vivillon, Litleo, Pyroar, Flabébé, Floette, Florges, Skiddo, Gogoat, Pancham, Pangoro, Furfrou, Espurr, Meowstic, Honedge, Doublade, Aegislash, Spritzee, Aromatisse, Swirlix, Slurpuff, Inkay, Malamar, Binacle, Barbaracle, Skrelp, Dragalge, Clauncher, Clawitzer, Helioptile, Heliolisk, Tyrunt, Tyrantrum, Amaura, Aurorus, Sylveon, Hawlucha, Dedenne, Carbink, Goomy, Sliggoo, Goodra, Klefki, Phantump, Trevenant, Pumpkaboo, Gourgeist, Bergmite, Avalugg, Noibat, Noivern, Xerneas, Yveltal, Zygarde, Diancie, Hoopa, Volcanion, Rowlet, Dartrix, Decidueye, Litten, Torracat, Incineroar, Popplio, Brionne, Primarina, Pikipek, Trumbeak, Toucannon, Yungoos, Gumshoos, Grubbin, Charjabug, Vikavolt, Crabrawler, Crabominable, Oricorio, Cutiefly, Ribombee, Rockruff, Lycanroc, Wishiwashi, Mareanie, Toxapex, Mudbray, Mudsdale, Dewpider, Araquanid, Fomantis, Lurantis, Morelull, Shiinotic, Salandit, Salazzle, Stufful, Bewear, Bounsweet, Steenee, Tsareena, Comfey, Oranguru, Passimian, Wimpod, Golisopod, Sandygast, Palossand, Pyukumuku, Type: Null, Silvally, Minior, Komala, Turtonator, Togedemaru, Mimikyu, Bruxish, Drampa, Dhelmise, Jangmo-o, Hakamo-o, Kommo-o, Tapu Koko, Tapu Lele, Tapu Bulu, Tapu Fini, Cosmog, Cosmoem, Solgaleo, Lunala, Nihilego, Buzzwole, Pheromosa, Xurkitree, Celesteela, Kartana, Guzzlord, Necrozma, Magearna, Marshadow, Poipole, Naganadel, Stakataka, Blacephalon, Zeraora, Meltan, Melmetal, Grookey, Thwackey, Rillaboom, Scorbunny, Raboot, Cinderace, Sobble, Drizzile, Inteleon, Skwovet, Greedent, Rookidee, Corvisquire, Corviknight, Blipbug, Dottler, Orbeetle, Nickit, Thievul, Gossifleur, Eldegoss, Wooloo, Dubwool, Chewtle, Drednaw, Yamper, Boltund, Rolycoly, Carkol, Coalossal, Applin, Flapple, Appletun, Silicobra, Sandaconda, Cramorant, Arrokuda, Barraskewda, Toxel, Toxtricity, Sizzlipede, Centiskorch, Clobbopus, Grapploct, Sinistea, Polteageist, Hatenna, Hattrem, Hatterene, Impidimp, Morgrem, Grimmsnarl, Obstagoon, Perrserker, Cursola, Sirfetch’d, Mr. Rime, Runerigus, Milcery, Alcremie, Falinks, Pincurchin, Snom, Frosmoth, Stonjourner, Eiscue, Indeedee, Morpeko, Cufant, Copperajah, Dracozolt, Arctozolt, Dracovish, Arctovish, Duraludon, Dreepy, Drakloak, Dragapult, Zacian, Zamazenta, Eternatus, Kubfu, Urshifu, Zarude";
    public static String LegList = " Zapdos, Moltres, Articuno, Mewtwo, Raikou, Suicune, Entei, Lugia, Ho-oh, Regirock, Registeel, Regice, Latios, Latias, Groudon, Kyorge, Rayquaza, Uxie, Mespirit, Azelf, Palkia, Giratina, Dialga, Creselia, Hetran, Regigigas, Terrakion, Cobalion, Verizion, Landorus, Thunderus, Tornadus, Reshiram, Zekrom, Kyurem, Xerneas, Yveltal, Zygarde, Type: Null, Silvally, Tapu-Koko, Tapu-Fini, Tapu-Bulu, Tapu-Lele, Sogaleo, Lunala, Necrozma, Cosmog, Cosmoem, Zacian, Zamazenta, Eturnatus, Mew, Celebi, Jirachi, Deoxys, Phione, Manaphy, Darkrai, Shaymin, Arceus, Victini, Keldeo, Meloetta, Genesect, Diancie, Hoopa, Volcanion, Magerna, Marshadow, Zeraora, Meltan, Melmetal,";
    Connection connect = null;
    Statement statmt = null;
    Connection connect2 = null;
    Statement statmt2 = null;
    Connection connect3 = null;
    Statement statmt3 = null;
    public static Integer durum;
    public static String[] gunlukgorevler = new String[]{"pokeavla"};
    static final String USER = "sa";
    static final String PASS = "";
    @Nonnull
    public static Map<UUID, Map<String, String>> gg = null;
    public static Yogurtlugorev instance;
    @Inject
    public Yogurtlugorev() {
        instance = this;
    }

    public static Boolean databaseEnabled = false;

    @Listener
    public void onServerStart(GamePreInitializationEvent event) {
        this.gg = Maps.newHashMap();
        Connection connect = null;
        Statement statmt = null;
        try {
            File file = new File(DB_URL3);
            if (file.exists()) {
                System.out.print("Database kaydi mevcut.");
            } else {
                /*Class.forName(JDBC_DRIVER);
                System.out.println("Database'e baglaniliyor.");
                connect = DriverManager.getConnection(DB_URL3, USER, PASS);
                statmt = connect.createStatement();*/

                Class.forName(JDBC_DRIVER);
                System.out.println("Database'e baglaniliyor.");
                try {
                    connect = DriverManager.getConnection(DB_URL3, USER, PASS);
                    statmt = connect.createStatement();
                    if(connect.isClosed() == false) {
                        connect.close();
                    }
                    if(statmt.isClosed() == false) {
                        statmt.close();
                    }
                    if(Files.exists(new File("./kadayifdb/backup/kadayifquest.mv.db").toPath())) {
                        File eskifile = new File("./kadayifdb/backup/kadayifquest.mv.db");
                        if(Files.exists(new File("./kadayifdb/backup/kadayifquest.mv.db.old").toPath())) {
                            File delfile = new File("./kadayifdb/backup/kadayifquest.mv.db.old");
                            delfile.delete();
                        }
                        eskifile.renameTo(new File("./kadayifdb/backup/kadayifquest.mv.db.old"));
                    }
                    Files.copy(new File("./kadayifdb/kadayifquest.mv.db").toPath(), new File("./kadayifdb/backup/kadayifquest.mv.db").toPath());
                    System.out.println("Backup dosyasi olusturuldu.");
                    connect = DriverManager.getConnection(DB_URL3, USER, PASS);
                    statmt = connect.createStatement();
                    databaseEnabled = true;
                } catch (SQLException sqlex) {
                    System.out.println(" ");
                    System.out.println("| HATA | -> SQL Database dosyasi bozuk, backuptan baslatilmaya calisiliyor.");
                    System.out.println(" ");
                    databaseEnabled = false;
                    if(Files.exists(new File("./kadayifdb/backup/kadayifquest.mv.db").toPath())) {
                        File delfile = new File("./kadayifdb/kadayifquest.mv.db");
                        delfile.delete();
                        Files.copy(new File("./kadayifdb/backup/kadayifquest.mv.db").toPath(), new File("./kadayifdb/kadayifquest.mv.db").toPath());
                        System.out.println("Backup ile baslamaya calisiliyor.");
                        try {
                            connect = DriverManager.getConnection(DB_URL3, USER, PASS);
                            statmt = connect.createStatement();
                            databaseEnabled = true;
                        } catch (SQLException sqlException) {
                            System.out.println(" ");
                            System.out.println("| HATA | -> Backup dosyasi bozuk, eski backup deneniyor.");
                            System.out.println(" ");
                            if(Files.exists(new File("./kadayifdb/backup/kadayifquest.mv.db.old").toPath())) {
                                delfile.delete();
                                Files.copy(new File("./kadayifdb/backup/kadayifquest.mv.db.old").toPath(), new File("./kadayifdb/kadayifquest.mv.db").toPath());
                                System.out.println("Eski backup ile baslamaya calisiliyor.");
                                try {
                                    connect = DriverManager.getConnection(DB_URL3, USER, PASS);
                                    statmt = connect.createStatement();
                                    databaseEnabled = true;
                                } catch (SQLException sqlException2) {
                                    System.out.println(" ");
                                    System.out.println("| HATA | -> Eski backup dosyasi bozuk, database sifirdan olusturuluyor.");
                                    System.out.println(" ");
                                    databaseEnabled = false;
                                    try {
                                        if(Files.exists(new File("./kadayifdb/kadayifquest.mv.db").toPath())) {
                                            delfile.delete();
                                        }

                                        connect = DriverManager.getConnection(DB_URL3, USER, PASS);
                                        statmt = connect.createStatement();
                                        System.out.println("Database olusturuldu.");
                                        databaseEnabled = true;
                                    } catch (SQLException sqlException3) {
                                        System.out.println(" ");
                                        System.out.println("| HATA | -> Database olusturulamadi, plugin aktif degil.");
                                        System.out.println(" ");
                                        databaseEnabled = false;
                                    }
                                }
                            }
                        }
                    } else {
                        try {
                            if(Files.exists(new File("./kadayifdb/kadayifquest.mv.db").toPath())) {
                                File delfile = new File("./kadayifdb/kadayifquest.mv.db");
                                delfile.delete();
                            }
                            connect = DriverManager.getConnection(DB_URL3, USER, PASS);
                            statmt = connect.createStatement();
                            System.out.println("Database olusturuldu.");
                            databaseEnabled = true;
                        } catch(SQLException sqlException) {
                            System.out.println(" ");
                            System.out.println("| HATA | -> Database olusturulamadi, plugin aktif degil.");
                            System.out.println(" ");
                            databaseEnabled = false;
                        }
                    }
                }
            }

            File file2 = new File("./kadayifdb/kadayifquest.trace.db");
            if (file2.exists()) {
                file2.delete();
                System.out.println("Trace dosyasi silindi.");
            } else {
                System.out.println("Trace dosyasi bulunamadi.");
            }
            String sql = "CREATE TABLE IF NOT EXISTS   Gorev " +
                    "(id INTEGER not NULL, " +
                    " oyuncu VARCHAR(255), " +
                    " pokeavcisi VARCHAR(255), " +
                    " pokebreed VARCHAR(255), " +
                    " pokehunter VARCHAR(255), " +
                    " evolution VARCHAR(255), " +
                    " shinyavcisi VARCHAR(255), " +
                    " legavcisi VARCHAR(255), " +
                    " bossavcisi VARCHAR(255), " +
                    " balikci VARCHAR(255), " +
                    " madenci VARCHAR(255), " +
                    " oduncu VARCHAR(255), " +
                    " dogasever VARCHAR(255), " +
                    " megaavcisi VARCHAR(255), " +
                    " pixelmadenci VARCHAR(255), " +
                    " pokedex VARCHAR(255), " +
                    " gorev1durum INTEGER, " +
                    " gorev2durum INTEGER, " +
                    " gorev1 VARCHAR(255), " +
                    " gorev2 VARCHAR(255))";
            statmt.executeUpdate(sql);

            connect = DriverManager.getConnection(DB_URL3, USER, PASS);
            statmt = connect.createStatement();

            String sql2 = "CREATE TABLE IF NOT EXISTS  GunlukGorev " +
                    "(id INTEGER not NULL, " +
                    " durum INTEGER, " +
                    " gorev VARCHAR(255), " +
                    " gorevpokemon VARCHAR(255), " +
                    " gorevtotalivs VARCHAR(255))";
            statmt.executeUpdate(sql2);

            Random r = new Random();
            int random = r.nextInt(gunlukgorevler.length);


            String ggAra = "SELECT * FROM GunlukGorev WHERE id='0'";
            ResultSet rsara2 = statmt.executeQuery(ggAra);
            if(rsara2.next() == false) {
                String sqlistenenler = "INSERT INTO GunlukGorev(id, durum, gorev, gorevpokemon, gorevtotalivs) VALUES" + "(?,?,?,?,?)";
                PreparedStatement preparedStatement = connect.prepareStatement(sqlistenenler);
                preparedStatement.setInt(1,0);
                preparedStatement.setInt(2, 1);
                preparedStatement.setString(3, gunlukgorevler[random]);
                if(gunlukgorevler[random].equals("pokeavla")) {
                    String[] pList = pokeList.split(", ");
                    int randompoke = r.nextInt(pList.length);
                    preparedStatement.setString(4, pList[randompoke]);
                    preparedStatement.setString(5, String.valueOf(r.nextInt(100)));
                    preparedStatement.executeUpdate();
                }
            } else {
                String sql3 = "UPDATE GunlukGorev SET gorev=?, gorevpokemon=?, gorevtotalivs=? WHERE id='0'";
                PreparedStatement ps = connect.prepareStatement(sql3);
                ps.setString(1, gunlukgorevler[random]);
                if(gunlukgorevler[random].equals("pokeavla")) {
                    String[] pList = pokeList.split(", ");
                    int randompoke = r.nextInt(pList.length);
                    ps.setString(2, pList[randompoke]);
                    ps.setString(3, String.valueOf(r.nextInt(100)));
                } else {
                    ps.setString(2, "");
                    ps.setString(3, "");
                }
                ps.executeUpdate();
            }

            String iAra = "SELECT * FROM Gorev WHERE id='0'";
            ResultSet rsara = statmt.executeQuery(iAra);
            if(rsara.next() == false) {
                String sqlistenenler = "INSERT INTO Gorev(id, oyuncu, pokeavcisi, pokebreed, pokehunter, evolution, shinyavcisi, legavcisi, bossavcisi, balikci, madenci, oduncu, dogasever, megaavcisi, pokedex, gorev1durum, gorev2durum, gorev1, gorev2) VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connect.prepareStatement(sqlistenenler);
                preparedStatement.setInt(1,0);
                preparedStatement.setString(2, null);
                preparedStatement.setString(3,"");
                preparedStatement.setString(4, "");
                preparedStatement.setString(5, "");
                preparedStatement.setString(6,"");
                preparedStatement.setString(7, "");
                preparedStatement.setString(8, "");
                preparedStatement.setString(9,"");
                preparedStatement.setString(10, "");
                preparedStatement.setString(11, "");
                preparedStatement.setString(12,"");
                preparedStatement.setString(13, "");
                preparedStatement.setString(14, "");
                preparedStatement.setString(15, "");
                preparedStatement.setInt(16, 0);
                preparedStatement.setInt(17, 0);
                preparedStatement.setString(18, "");
                preparedStatement.setString(19, "");
                preparedStatement.executeUpdate();
            }

            System.out.println("Verilen databasede tableler olusturuldu.");
            statmt.close();
            statmt.close();

        }catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(statmt!=null) statmt.close();
            } catch(SQLException se2) {
            }
            try {
                if(statmt!=null) statmt.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event, @First Player p) {
        final UUID uuid = p.getUniqueId();
        if (!this.gg.containsKey(uuid)) {
            this.gg.put(uuid, Maps.newHashMap());
        }
        this.gg.get(uuid).put("gunlukgorev:", "0");
        this.gg.get(uuid).put("gorev1durum:", "0");
        this.gg.get(uuid).put("gorev2durum:", "0");
        try {
            connect = DriverManager.getConnection(DB_URL3, USER, PASS);
            statmt = connect.createStatement();
            try {
                statmt = connect.createStatement();
                String sqlrs = "SELECT * FROM Gorev WHERE oyuncu='" + p.getName() + "'";
                ResultSet rs = statmt.executeQuery(sqlrs);
                if(rs.next()) {
                    this.gg.get(uuid).put("gorev1durum:", String.valueOf(rs.getInt("gorev1durum")));
                    this.gg.get(uuid).put("gorev2durum:", String.valueOf(rs.getInt("gorev2durum")));
                }

                statmt.close();
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

    @Listener
    public void onInit(@Nullable final GameStartedServerEvent e) {
        Sponge.getCommandManager().register(this, myCommandSpec, "quest");
        Sponge.getCommandManager().register(this, pokedex, "pokedex");
        Pixelmon.EVENT_BUS.register(this);
        Sponge.getEventManager().registerListeners(this, new GorevChangeBlock());
        Sponge.getEventManager().registerListeners(this, new GorevFishing());
        Pixelmon.EVENT_BUS.register(new GorevFishing());
        Pixelmon.EVENT_BUS.register(new GorevHatch());
        Pixelmon.EVENT_BUS.register(new GorevCatch());
        Pixelmon.EVENT_BUS.register(new GorevBeat());
        Pixelmon.EVENT_BUS.register(new GorevEvolution());
    }

    @Listener
    public void onServerStop(GameStoppingServerEvent event){
        try{
            if(statmt!=null) statmt.close();
            if(statmt2!=null) statmt2.close();
            if(statmt3!=null) statmt3.close();
        } catch(SQLException se2) {
        }
        try {
            if(connect!=null) connect.close();
            if(connect2!=null) connect2.close();
            if(connect3!=null) connect3.close();
        } catch(SQLException se){
            se.printStackTrace();
        }
    }


    CommandSpec gunlukgorevdurum = CommandSpec.builder()
            .description(Text.of("Gunluk gorevi acip kapatir."))
            .permission("admin.gunlukgorevdurum")
            .executor((CommandSource src, CommandContext args) -> {
                src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "kadayifQuest v1.0 by YogurtluKadayif"));
                try {
                    connect = DriverManager.getConnection(DB_URL3, USER, PASS);
                    statmt = connect.createStatement();
                    try {
                        Class.forName(JDBC_DRIVER);

                        statmt = connect.createStatement();
                        String sqlrs2 = "SELECT * FROM GunlukGorev WHERE id='0'";
                        ResultSet rs2 = statmt.executeQuery(sqlrs2);

                        if(rs2.next()) {
                            String sql3 = "UPDATE GunlukGorev SET durum=? WHERE id='0'";
                            PreparedStatement ps = connect.prepareStatement(sql3);
                            if(rs2.getInt("durum") == 1) {
                                ps.setInt(1, 0);
                                src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gunluk gorev kapatildi."));
                            } else {
                                if(rs2.getInt("durum") == 0) {
                                    ps.setInt(1, 1);
                                    src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gunluk gorev acildi."));
                                }
                            }
                            ps.executeUpdate();
                        }

                    }catch (SQLException se) {
                        se.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (statmt != null) statmt.close();
                            if (statmt2 != null) statmt2.close();
                            if (statmt3 != null) statmt3.close();
                        } catch (SQLException se2) {
                        }
                        try {
                            if (connect != null) connect.close();
                            if (connect2 != null) connect2.close();
                            if (connect3 != null) connect3.close();
                        } catch (SQLException se) {
                            se.printStackTrace();
                        }
                    }
                } catch(SQLException se) {
                    se.printStackTrace();
                } catch(Exception e) {
                    e.printStackTrace();
                }
                return CommandResult.success();
            })
            .build();

    CommandSpec gunlukgorevreset = CommandSpec.builder()
            .description(Text.of("Gunluk gorevi yeniden olusturur."))
            .permission("admin.gunlukgorevreset")
            .executor((CommandSource src, CommandContext args) -> {
                src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "kadayifQuest v1.0 by YogurtluKadayif"));
                try {
                    connect = DriverManager.getConnection(DB_URL3, USER, PASS);
                    statmt = connect.createStatement();
                    try {
                        Class.forName(JDBC_DRIVER);

                        statmt = connect.createStatement();
                        String sqlrs2 = "SELECT * FROM GunlukGorev WHERE id='0'";
                        ResultSet rs2 = statmt.executeQuery(sqlrs2);

                        if(rs2.next()) {
                            String sql3 = "UPDATE GunlukGorev SET gorev=?, gorevpokemon=?, gorevtotalivs=? WHERE id='0'";
                            PreparedStatement ps = connect.prepareStatement(sql3);
                            Random r = new Random();
                            int random = r.nextInt(gunlukgorevler.length);
                            ps.setString(1, gunlukgorevler[random]);
                            if(gunlukgorevler[random].equals("pokeavla")) {
                                String[] pList = pokeList.split(", ");
                                int randompoke = r.nextInt(pList.length);
                                int ivs = r.nextInt(100);
                                ps.setString(2, pList[randompoke]);
                                ps.setString(3, String.valueOf(ivs));
                                src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gunluk gorev sifirlandi. Yeni gorev: Pokemon Avla, pokemon: " + pList[randompoke] + ", min. ivs: " + ivs));
                            } else {
                                ps.setString(2, "");
                                ps.setString(3, "");
                            }
                            ps.executeUpdate();

                        }

                    }catch (SQLException se) {
                        se.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (statmt != null) statmt.close();
                            if (statmt2 != null) statmt2.close();
                            if (statmt3 != null) statmt3.close();
                        } catch (SQLException se2) {
                        }
                        try {
                            if (connect != null) connect.close();
                            if (connect2 != null) connect2.close();
                            if (connect3 != null) connect3.close();
                        } catch (SQLException se) {
                            se.printStackTrace();
                        }
                    }
                } catch(SQLException se) {
                    se.printStackTrace();
                } catch(Exception e) {
                    e.printStackTrace();
                }
                return CommandResult.success();
            })
            .build();

    CommandSpec gunlukgorev = CommandSpec.builder()
            .description(Text.of("Gunluk gorevi gosterir."))
            .permission("kadayifquest.gunlukgorev")
            .executor((CommandSource src, CommandContext args) -> {
                //src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "kadayifQuest v1.0 by YogurtluKadayif"));
                try {
                    connect = DriverManager.getConnection(DB_URL3, USER, PASS);
                    statmt = connect.createStatement();
                    try {
                        Class.forName(JDBC_DRIVER);

                        statmt = connect.createStatement();
                        String sqlrs2 = "SELECT * FROM GunlukGorev WHERE id='0'";
                        ResultSet rs2 = statmt.executeQuery(sqlrs2);

                        if(rs2.next()) {
                            String gorev1 = rs2.getString("gorev");
                            if (gorev1.equals("pokeavla")) {
                                gorev1 = "Pokemon Avla";
                                String ypoke = rs2.getString("gorevpokemon");
                                Integer minivs = Integer.valueOf(rs2.getString("gorevtotalivs"));
                                Integer durum = rs2.getInt("durum");
                                if (durum == 0) {
                                    src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gunluk gorev ozelligi kapali."));
                                } else {
                                    if(durum == 1) {
                                        if(minivs >= 50) {
                                            src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bugunun gorevi: " + gorev1 + " | Yakalanacak pokemon: " + ypoke + "  || Minimum IVS: " + minivs));
                                        } else {
                                            if(minivs < 50) {
                                                src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Bugunun gorevi: " + gorev1 + " | Yakalanacak pokemon: " + ypoke + "  || Maksimum IVS: " + minivs));
                                            }
                                        }
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
                            if (statmt2 != null) statmt2.close();
                            if (statmt3 != null) statmt3.close();
                        } catch (SQLException se2) {
                        }
                        try {
                            if (connect != null) connect.close();
                            if (connect2 != null) connect2.close();
                            if (connect3 != null) connect3.close();
                        } catch (SQLException se) {
                            se.printStackTrace();
                        }
                    }
                } catch(SQLException se) {
                    se.printStackTrace();
                } catch(Exception e) {
                    e.printStackTrace();
                }
                return CommandResult.success();
            })
            .build();


    CommandSpec gorevvazgec = CommandSpec.builder()
            .description(Text.of("Gorevlerinizden vazgecmenizi saglar."))
            .permission("kadayifquest.gorevvazgec")
            .arguments(
                    GenericArguments.onlyOne(GenericArguments.integer(Text.of("sayi")))
            )
            .executor((CommandSource src, CommandContext args) -> {
                Integer sayi = args.<Integer>getOne("sayi").get();
                //src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "kadayifQuest v1.0 by YogurtluKadayif"));
                try {
                    connect = DriverManager.getConnection(DB_URL3, USER, PASS);
                    statmt = connect.createStatement();
                    try {
                        Class.forName(JDBC_DRIVER);

                        statmt = connect.createStatement();
                        String sqlrs2 = "SELECT * FROM Gorev WHERE oyuncu='" + src.getName() + "'";
                        ResultSet rs2 = statmt.executeQuery(sqlrs2);

                        if(rs2.next()) {
                            if(sayi == 1) {
                                String gorev1 = rs2.getString("gorev1");
                                String gorev = "yok";
                                if (gorev1.equals("pokeavcisi")) {
                                    gorev = "Pokemon Avcisi";
                                } else {
                                    if (gorev1.equals("pokebreed")) {
                                        gorev = "Pokemon Ureticisi";
                                    } else {
                                        if (gorev1.equals("pokehunter")) {
                                            gorev = "Pokemon Azaltici";
                                        } else {
                                            if (gorev1.equals("evolution")) {
                                                gorev = "Pokemon Evrimcisi";
                                            } else {
                                                if (gorev1.equals("shinyavcisi")) {
                                                    gorev = "Shiny Avcisi";
                                                } else {
                                                    if (gorev1.equals("legavcisi")) {
                                                        gorev = "Leg Avcisi";
                                                    } else {
                                                        if (gorev1.equals("bossavcisi")) {
                                                            gorev = "Boss Avcisi";
                                                        } else {
                                                            if (gorev1.equals("balikci")) {
                                                                gorev = "Balikci";
                                                            } else {
                                                                if (gorev1.equals("madenci")) {
                                                                    gorev = "Madenci";
                                                                } else {
                                                                    if (gorev1.equals("oduncu")) {
                                                                        gorev = "Oduncu";
                                                                    } else {
                                                                        if (gorev1.equals("dogasever")) {
                                                                            gorev = "Dogasever";
                                                                        } else {
                                                                            if (gorev1.equals("megaavcisi")) {
                                                                                gorev = "Mega Avcisi";
                                                                            } else {
                                                                                if (gorev1.equals("pokedex")) {
                                                                                    gorev = "Pokemon Koleksiyoncusu";
                                                                                } else {
                                                                                    if (gorev1.equals("yok")) {
                                                                                        gorev = "Yok";
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
                                statmt = connect.createStatement();
                                if (!gorev1.equals("yok")) {
                                    if (rs2.getString(gorev1).equals("g1devam")) {
                                        String sql2 = "UPDATE Gorev SET  " + gorev1 + "=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + src.getName() + "'";
                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                        ps2.setString(1, "baslamadi");
                                        ps2.executeUpdate();
                                        src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, gorev + " isimli gorevinizden vazgectiniz."));
                                    } else {
                                        if (rs2.getString(gorev1).equals("g2devam")) {
                                            String sql2 = "UPDATE Gorev SET  " + gorev1 + "=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + src.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "g1bitti");
                                            ps2.executeUpdate();
                                            src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, gorev + " isimli gorevinizden vazgectiniz."));
                                        } else {
                                            if (rs2.getString(gorev1).equals("g3devam")) {
                                                String sql2 = "UPDATE Gorev SET  " + gorev1 + "=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + src.getName() + "'";
                                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                ps2.setString(1, "g2bitti");
                                                ps2.executeUpdate();
                                                src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, gorev + " isimli gorevinizden vazgectiniz."));
                                            } else {
                                                if (rs2.getString(gorev1).equals("g4devam")) {
                                                    String sql2 = "UPDATE Gorev SET  " + gorev1 + "=?, gorev1durum='0', gorev1='yok' WHERE oyuncu='" + src.getName() + "'";
                                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                    ps2.setString(1, "g3bitti");
                                                    ps2.executeUpdate();
                                                    src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, gorev + " isimli gorevinizden vazgectiniz."));
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                if(sayi == 2) {
                                    String gorev2 = rs2.getString("gorev2");
                                    String gorev = "yok";
                                    if (gorev2.equals("pokeavcisi")) {
                                        gorev = "Pokemon Avcisi";
                                    } else {
                                        if (gorev2.equals("pokebreed")) {
                                            gorev = "Pokemon Ureticisi";
                                        } else {
                                            if (gorev2.equals("pokehunter")) {
                                                gorev = "Pokemon Azaltici";
                                            } else {
                                                if (gorev2.equals("evolution")) {
                                                    gorev = "Pokemon Evrimcisi";
                                                } else {
                                                    if (gorev2.equals("shinyavcisi")) {
                                                        gorev = "Shiny Avcisi";
                                                    } else {
                                                        if (gorev2.equals("legavcisi")) {
                                                            gorev = "Leg Avcisi";
                                                        } else {
                                                            if (gorev2.equals("bossavcisi")) {
                                                                gorev = "Boss Avcisi";
                                                            } else {
                                                                if (gorev2.equals("balikci")) {
                                                                    gorev = "Balikci";
                                                                } else {
                                                                    if (gorev2.equals("madenci")) {
                                                                        gorev = "Madenci";
                                                                    } else {
                                                                        if (gorev2.equals("oduncu")) {
                                                                            gorev = "Oduncu";
                                                                        } else {
                                                                            if (gorev2.equals("dogasever")) {
                                                                                gorev = "Dogasever";
                                                                            } else {
                                                                                if (gorev2.equals("megaavcisi")) {
                                                                                    gorev = "Mega Avcisi";
                                                                                } else {
                                                                                    if (gorev2.equals("pokedex")) {
                                                                                        gorev = "Pokemon Koleksiyoncusu";
                                                                                    } else {
                                                                                        if (gorev2.equals("yok")) {
                                                                                            gorev = "Yok";
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
                                    statmt = connect.createStatement();
                                    if(!gorev2.equals("yok")) {
                                        if (rs2.getString(gorev2).equals("g1devam")) {
                                            String sql2 = "UPDATE Gorev SET  " + gorev2 + "=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + src.getName() + "'";
                                            PreparedStatement ps2 = connect.prepareStatement(sql2);
                                            ps2.setString(1, "baslamadi");
                                            ps2.executeUpdate();
                                            src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, gorev + " isimli gorevinizden vazgectiniz."));
                                        } else {
                                            if (rs2.getString(gorev2).equals("g2devam")) {
                                                String sql2 = "UPDATE Gorev SET  " + gorev2 + "=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + src.getName() + "'";
                                                PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                ps2.setString(1, "g1bitti");
                                                ps2.executeUpdate();
                                                src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, gorev + " isimli gorevinizden vazgectiniz."));
                                            } else {
                                                if (rs2.getString(gorev2).equals("g3devam")) {
                                                    String sql2 = "UPDATE Gorev SET  " + gorev2 + "=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + src.getName() + "'";
                                                    PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                    ps2.setString(1, "g2bitti");
                                                    ps2.executeUpdate();
                                                    src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, gorev + " isimli gorevinizden vazgectiniz."));
                                                } else {
                                                    if (rs2.getString(gorev2).equals("g4devam")) {
                                                        String sql2 = "UPDATE Gorev SET  " + gorev2 + "=?, gorev2durum='0', gorev2='yok' WHERE oyuncu='" + src.getName() + "'";
                                                        PreparedStatement ps2 = connect.prepareStatement(sql2);
                                                        ps2.setString(1, "g3bitti");
                                                        ps2.executeUpdate();
                                                        src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, gorev + " isimli gorevinizden vazgectiniz."));
                                                    }
                                                }
                                            }
                                        }
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
                            if (statmt2 != null) statmt2.close();
                            if (statmt3 != null) statmt3.close();
                        } catch (SQLException se2) {
                        }
                        try {
                            if (connect != null) connect.close();
                            if (connect2 != null) connect2.close();
                            if (connect3 != null) connect3.close();
                        } catch (SQLException se) {
                            se.printStackTrace();
                        }
                    }
                } catch(SQLException se) {
                    se.printStackTrace();
                } catch(Exception e) {
                    e.printStackTrace();
                }
                return CommandResult.success();
            })
            .build();

    CommandSpec gorevliste = CommandSpec.builder()
            .description(Text.of("Gorevlerinizi gosterir."))
            .permission("kadayifquest.gorevliste")
            .executor((CommandSource src, CommandContext args) -> {
                //src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "kadayifQuest v1.0 by YogurtluKadayif"));
                try {
                    connect = DriverManager.getConnection(DB_URL3, USER, PASS);
                    statmt = connect.createStatement();
                    try {
                        Class.forName(JDBC_DRIVER);

                        statmt = connect.createStatement();
                        String sqlrs2 = "SELECT * FROM Gorev WHERE oyuncu='" + src.getName() + "'";
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
                                                                            if(gorev1.equals("yok")) {
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
                                                                            if(gorev2.equals("yok")) {
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
                            src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorev 1: " + gorev1 + " | Ilerlemeniz: " + gorev1durum));
                            src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Gorev 2: " + gorev2 + " | Ilerlemeniz: " + gorev2durum));

                        }

                    }catch (SQLException se) {
                        se.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (statmt != null) statmt.close();
                            if (statmt2 != null) statmt2.close();
                            if (statmt3 != null) statmt3.close();
                        } catch (SQLException se2) {
                        }
                        try {
                            if (connect != null) connect.close();
                            if (connect2 != null) connect2.close();
                            if (connect3 != null) connect3.close();
                        } catch (SQLException se) {
                            se.printStackTrace();
                        }
                    }
                } catch(SQLException se) {
                    se.printStackTrace();
                } catch(Exception e) {
                    e.printStackTrace();
                }
                return CommandResult.success();
            })
            .build();

    CommandSpec myCommandSpec = CommandSpec.builder()
            .description(Text.of("Gorev menusunu acar"))
            .permission("kadayifquest.gorev")
            .child(gorevliste, "list")
            .child(gorevvazgec, "abandon")
            .child(gunlukgorev, "gunluk")
            .child(gunlukgorevreset, "adgreset")
            .child(gunlukgorevdurum, "adgdurum")
            .executor((CommandSource src, CommandContext args) -> {
                src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "kadayifQuest v1.0 by YogurtluKadayif"));
                src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, " Gorevlerinizi bitirmeden once envanterinizde en az 2 bosluk birakin, esya kayiplarindan yetkililer sorumlu degildir."));
                try {
                    connect = DriverManager.getConnection(DB_URL3, USER, PASS);
                    statmt = connect.createStatement();
                    try {
                        Class.forName(JDBC_DRIVER);

                        String sqlrs1 = "SELECT id FROM Gorev ORDER BY id DESC";
                        ResultSet rs1 = statmt.executeQuery(sqlrs1);

                        rs1.next();
                        int idr1 = rs1.getInt("id");
                        int id = idr1 + 1;

                        statmt = connect.createStatement();
                        String sqlrs2 = "SELECT * FROM Gorev WHERE oyuncu='" + src.getName() + "'";
                        ResultSet rs2 = statmt.executeQuery(sqlrs2);

                        if(!rs2.next()) {
                            String gorevkayit = "INSERT INTO Gorev(id, oyuncu, pokeavcisi, pokebreed, pokehunter, evolution, shinyavcisi, legavcisi, bossavcisi, balikci, madenci, oduncu, dogasever, megaavcisi, pokedex, gorev1durum, gorev2durum, gorev1, gorev2) VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                            PreparedStatement preparedStatement2 = connect.prepareStatement(gorevkayit);
                            preparedStatement2.setInt(1,id);
                            preparedStatement2.setString(2, src.getName());
                            preparedStatement2.setString(3,"baslamadi");
                            preparedStatement2.setString(4, "baslamadi");
                            preparedStatement2.setString(5, "baslamadi");
                            preparedStatement2.setString(6,"baslamadi");
                            preparedStatement2.setString(7, "baslamadi");
                            preparedStatement2.setString(8, "baslamadi");
                            preparedStatement2.setString(9,"baslamadi");
                            preparedStatement2.setString(10, "baslamadi");
                            preparedStatement2.setString(11, "baslamadi");
                            preparedStatement2.setString(12,"baslamadi");
                            preparedStatement2.setString(13, "baslamadi");
                            preparedStatement2.setString(14, "baslamadi");
                            preparedStatement2.setString(15, "baslamadi");
                            preparedStatement2.setInt(16, 0);
                            preparedStatement2.setInt(17, 0);
                            preparedStatement2.setString(18, "yok");
                            preparedStatement2.setString(19, "yok");
                            preparedStatement2.executeUpdate();
                        }
                        final Player player = (Player) src;
                        final Gui gui = new Gui(player);
                        player.openInventory(gui.getbackpack());
                        //src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Goreviniz basladi."));

                    }catch (SQLException se) {
                        se.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (statmt != null) statmt.close();
                            if (statmt2 != null) statmt2.close();
                            if (statmt3 != null) statmt3.close();
                        } catch (SQLException se2) {
                        }
                        try {
                            if (connect != null) connect.close();
                            if (connect2 != null) connect2.close();
                            if (connect3 != null) connect3.close();
                        } catch (SQLException se) {
                            se.printStackTrace();
                        }
                    }
                } catch(SQLException se) {
                    se.printStackTrace();
                } catch(Exception e) {
                    e.printStackTrace();
                }
                return CommandResult.success();
            })
            .build();

    CommandSpec pokedex = CommandSpec.builder()
            .description(Text.of("Pokedex gorevleri menusunu acar"))
            .permission("kadayifquest.pokedexgui")
            .executor((CommandSource src, CommandContext args) -> {
                src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "kadayifQuest v1.0 by YogurtluKadayif"));
                src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, " Gorevlerinizi bitirmeden once envanterinizde en az 2 bosluk birakin, esya kayiplarindan yetkililer sorumlu degildir."));
                try {
                    connect = DriverManager.getConnection(DB_URL3, USER, PASS);
                    statmt = connect.createStatement();
                    try {
                        Class.forName(JDBC_DRIVER);

                        String sqlrs1 = "SELECT id FROM Gorev ORDER BY id DESC";
                        ResultSet rs1 = statmt.executeQuery(sqlrs1);

                        rs1.next();
                        int idr1 = rs1.getInt("id");
                        int id = idr1 + 1;

                        statmt = connect.createStatement();
                        String sqlrs2 = "SELECT * FROM Gorev WHERE oyuncu='" + src.getName() + "'";
                        ResultSet rs2 = statmt.executeQuery(sqlrs2);

                        if(!rs2.next()) {
                            String gorevkayit = "INSERT INTO Gorev(id, oyuncu, pokeavcisi, pokebreed, pokehunter, evolution, shinyavcisi, legavcisi, bossavcisi, balikci, madenci, oduncu, dogasever, megaavcisi, pokedex, gorev1durum, gorev2durum, gorev1, gorev2) VALUES" + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                            PreparedStatement preparedStatement2 = connect.prepareStatement(gorevkayit);
                            preparedStatement2.setInt(1,id);
                            preparedStatement2.setString(2, src.getName());
                            preparedStatement2.setString(3,"baslamadi");
                            preparedStatement2.setString(4, "baslamadi");
                            preparedStatement2.setString(5, "baslamadi");
                            preparedStatement2.setString(6,"baslamadi");
                            preparedStatement2.setString(7, "baslamadi");
                            preparedStatement2.setString(8, "baslamadi");
                            preparedStatement2.setString(9,"baslamadi");
                            preparedStatement2.setString(10, "baslamadi");
                            preparedStatement2.setString(11, "baslamadi");
                            preparedStatement2.setString(12,"baslamadi");
                            preparedStatement2.setString(13, "baslamadi");
                            preparedStatement2.setString(14, "baslamadi");
                            preparedStatement2.setString(15, "baslamadi");
                            preparedStatement2.setInt(16, 0);
                            preparedStatement2.setInt(17, 0);
                            preparedStatement2.setString(18, "yok");
                            preparedStatement2.setString(19, "yok");
                            preparedStatement2.executeUpdate();
                        }
                        final Player player = (Player) src;
                        final GuiPokedex2 gui = new GuiPokedex2(player);
                        player.openInventory(gui.getbackpack());
                        //src.sendMessage(Text.of(TextColors.AQUA, "[", TextColors.LIGHT_PURPLE, "kadayifQuest", TextColors.AQUA, "] ", TextColors.GREEN, "Goreviniz basladi."));

                    }catch (SQLException se) {
                        se.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (statmt != null) statmt.close();
                            if (statmt2 != null) statmt2.close();
                            if (statmt3 != null) statmt3.close();
                        } catch (SQLException se2) {
                        }
                        try {
                            if (connect != null) connect.close();
                            if (connect2 != null) connect2.close();
                            if (connect3 != null) connect3.close();
                        } catch (SQLException se) {
                            se.printStackTrace();
                        }
                    }
                } catch(SQLException se) {
                    se.printStackTrace();
                } catch(Exception e) {
                    e.printStackTrace();
                }
                return CommandResult.success();
            })
            .build();
}
