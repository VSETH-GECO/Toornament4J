import ch.ethz.geco.t4j.impl.ToornamentClient;
import ch.ethz.geco.t4j.obj.ICustomField;
import ch.ethz.geco.t4j.obj.IToornamentClient;
import ch.ethz.geco.t4j.obj.ITournament;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Main {
    static ObjectMapper mapper = new ObjectMapper();

    static final String disciplineJson = "{\"platforms_available\":[\"pc\",\"playstation4\",\"xbox_one\"],\"team_size\":{\"min\":6,\"max\":6},\"id\":\"overwatch\",\"name\":\"Overwatch\",\"shortname\":\"Overwatch\",\"fullname\":\"Overwatch\",\"copyrights\":\"Activision Blizzard\"}";
    static final String tournamentJson = "{\"id\":\"1233273985385799680\",\"discipline\":\"overwatch\",\"name\":\"Polylan Z\\u00fcrich 2018\",\"full_name\":\"Polylan Z\\u00fcrich 2018 Overwatch Tournament\",\"status\":\"completed\",\"scheduled_date_start\":\"2018-03-30\",\"scheduled_date_end\":\"2018-04-02\",\"timezone\":\"Europe\\/Zurich\",\"online\":true,\"public\":true,\"location\":\"ETH H\\u00f6nggerberg, HXE\",\"country\":\"CH\",\"size\":6,\"participant_type\":\"team\",\"organization\":\"VSETH GECo\",\"contact\":null,\"discord\":\"http:\\/\\/geco.ethz.ch\\/discord\",\"website\":\"https:\\/\\/geco.ethz.ch\",\"description\":\"PolyLAN 2018 Overwatch Tournament\",\"rules\":\"Most important rules:\\r\\n- Close to default competitive rule set\\r\\n-> Brigitte will not be allowed\\r\\n- Ban maps until one is left, that one will be played\\r\\n- Lobby will be hosted by one of the teams.\\r\\n- Pausing allowed because of technical problems.\\r\\n- All Assault, Escort, Assault Escort and Control maps allowed.\\r\\n\\r\\nFor details, please read the:\\r\\n\\r\\nGeneral tournament rules: https:\\/\\/geco.ethz.ch\\/GecoGTCs.pdf\\r\\nOverwatch specific rules: https:\\/\\/geco.ethz.ch\\/static\\/pdf\\/overwatch.pdf\",\"prize\":\"TBD\",\"platforms\":[\"pc\"],\"logo\":null,\"registration_enabled\":false,\"registration_opening_datetime\":null,\"registration_closing_datetime\":null}";
    static final String customFieldJson = "{\"id\":\"128114939547269789\",\"machine_name\":\"my_steam_id\",\"label\":\"MySteamID\",\"target_type\":\"player\",\"type\":\"steam_player_id\",\"default_value\":\"#steam_id\",\"required\":false,\"public\":false,\"position\":5}";
    static final String participantJson = "{\"id\":\"1315294721575297024\",\"name\":\"FightingPinguins\",\"custom_fields\":[],\"lineup\":[{\"name\":\"Lynxxx\",\"custom_fields\":[]},{\"name\":\"Zelos\",\"custom_fields\":[]},{\"name\":\"RiseBasti\",\"custom_fields\":[]},{\"name\":\"CommanderXXX\",\"custom_fields\":[]},{\"name\":\"capitain96\",\"custom_fields\":[]},{\"name\":\"lionqueen\",\"custom_fields\":[]}]}";
    static final String stageJson = "{\"id\":\"1315299555505135616\",\"number\":1,\"name\":\"Groups\",\"type\":\"pools\",\"closed\":false,\"settings\":{\"size\":6,\"arrival\":\"sequential\",\"departure\":\"mirror\",\"nb_groups\":2,\"calculators\":[{\"name\":\"match_result_points\",\"options\":{\"win\":3,\"draw\":1,\"loss\":0}}],\"tiebreakers\":[{\"name\":\"match_result_overall\",\"options\":[]},{\"name\":\"points_head_to_head\",\"options\":[]},{\"name\":\"manual\",\"options\":[]},{\"name\":\"match_score_difference_head_to_head\",\"options\":[]},{\"name\":\"match_score_difference_overall\",\"options\":[]}],\"group_naming\":\"number\",\"match_format\":\"one\",\"round_naming\":\"number\",\"pairing_method\":\"standard\"}}";
    static final String groupJson = "{\"id\":\"1315299556444659725\",\"stage_id\":\"1315299555505135616\",\"number\":1,\"name\":\"Group 1\",\"closed\":false,\"settings\":{\"size\":3,\"pairing_values\":[[[2,3]],[[1,3]],[[1,2]]]}}";
    static final String roundJson = "{\"id\":\"1315299556478214162\",\"group_id\":\"1315299556444659725\",\"stage_id\":\"1315299555505135616\",\"number\":1,\"name\":\"Round 1\",\"closed\":false,\"settings\":{\"size\":1}}";
    static final String matchJson = "{\"id\":\"1315299556511768595\",\"stage_id\":\"1315299555505135616\",\"group_id\":\"1315299556444659725\",\"round_id\":\"1315299556478214162\",\"number\":1,\"type\":\"duel\",\"status\":\"completed\",\"scheduled_datetime\":null,\"played_at\":\"2018-03-30T17:53:48+00:00\",\"opponents\":[{\"number\":1,\"participant\":{\"id\":\"1315165502507859968\",\"name\":\"Simple G\\u00fcnther\",\"custom_fields\":[]},\"result\":\"win\",\"forfeit\":false,\"score\":1},{\"number\":2,\"participant\":{\"id\":\"1315294721575297024\",\"name\":\"FightingPinguins\",\"custom_fields\":[]},\"result\":\"loss\",\"forfeit\":false,\"score\":0}],\"games\":[{\"number\":1,\"status\":\"pending\",\"opponents\":[{\"number\":1,\"result\":null,\"forfeit\":false,\"score\":null},{\"number\":2,\"result\":null,\"forfeit\":false,\"score\":null}]}]}";

    public static void main(String[] args) {
        IToornamentClient client = new ToornamentClient("-SVdxKS2G8Wxn8jyzhY4us59fjM-DVWc1iu_WTS6Ils");

        ITournament tournament = client.getTournamentByID(1233273985385799680L);

        //ToornamentUtils.getCustomFieldByJSON(null, null);

        List<ICustomField> customFields = tournament.getCustomFields();

        System.out.println(customFields.size());
        System.out.println(customFields.get(0).getName());
    }
}
