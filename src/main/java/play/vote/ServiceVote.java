package play.vote;

import org.glassfish.jersey.server.JSONP;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/8
 * Time: 12:01
 */
@Path("/vote")
@Produces("application/javascript")
public class ServiceVote {
    /**
     * RESTful API 的JSONP时的callback函数名称
     */
    private static final String JSONP_CALLBACK = "callback";

    @JSONP(queryParam = JSONP_CALLBACK)
    @GET
    @Path("getOne")
    public MyCampaignerEntity getOne(@QueryParam("id") int id) {
        return ManageVote.get(id);
    }

    @JSONP(queryParam = JSONP_CALLBACK)
    @GET
    @Path("getAll")
    public List<MyCampaignerEntity> getAll() {
        return ManageVote.get_All();
    }

    @JSONP(queryParam = JSONP_CALLBACK)
    @GET
    @Path("addOne")
    public int addOne(
            @QueryParam("name") String name,
            @QueryParam("des") String des,
            @QueryParam("picUrl") String picURL,
            @QueryParam("other1") String other1,
            @QueryParam("other2") String other2,
            @QueryParam("other3") String other3,
            @QueryParam("MM") String MM
    ) {
        if (!MM.equals(VoteBasicInfo.ManagePassword)) {
            return -1;
        }
        MyCampaignerEntity myCampaignerEntity = new MyCampaignerEntity();
        myCampaignerEntity.setVoteSum(0);
        myCampaignerEntity.setName(name);
        myCampaignerEntity.setDes(des);
        myCampaignerEntity.setPicUrl(picURL);
        myCampaignerEntity.setOther1(other1);
        myCampaignerEntity.setOther2(other2);
        myCampaignerEntity.setOther3(other3);
        ManageVote.add(myCampaignerEntity);
        return myCampaignerEntity.getId();
    }

    @JSONP(queryParam = JSONP_CALLBACK)
    @GET
    @Path("removeOne")
    public boolean removeOne(@QueryParam("id") int id, @QueryParam("MM") String MM) {
        return MM.equals(VoteBasicInfo.ManagePassword) && ManageVote.remove(id);
    }

    @JSONP(queryParam = JSONP_CALLBACK)
    @GET
    @Path("updateOne")
    public boolean updateOne(
            @QueryParam("id") int id,
            @QueryParam("name") String name,
            @QueryParam("des") String des,
            @QueryParam("picUrl") String picURL,
            @QueryParam("other1") String other1,
            @QueryParam("other2") String other2,
            @QueryParam("other3") String other3,
            @QueryParam("MM") String MM
    ) {
        if (!MM.equals(VoteBasicInfo.ManagePassword)) {
            return false;
        }
        MyCampaignerEntity myCampaignerEntity = new MyCampaignerEntity();
        myCampaignerEntity.setId(id);
        myCampaignerEntity.setName(name);
        myCampaignerEntity.setDes(des);
        myCampaignerEntity.setPicUrl(picURL);
        myCampaignerEntity.setOther1(other1);
        myCampaignerEntity.setOther2(other2);
        myCampaignerEntity.setOther3(other3);
        return ManageVote.change(myCampaignerEntity);
    }

    @JSONP(queryParam = JSONP_CALLBACK)
    @GET
    @Path("submitVote")
    public int submitVote(@QueryParam("XH") String XH, @QueryParam("MM") String MM, @QueryParam("ids") String ids) {
        String[] allID = ids.split(",");
        return ManageVote.vote(XH, MM, allID);
    }

    @JSONP(queryParam = JSONP_CALLBACK)
    @GET
    @Path("getBasicInfo")
    public VoteBasicInfo getBasicInfo() {
        return ManageVote.voteBasicInfo;
    }

    @JSONP(queryParam = JSONP_CALLBACK)
    @GET
    @Path("updateBasicInfo")
    public boolean updateBasicInfo(
            @QueryParam("name") String name,
            @QueryParam("des") String des,
            @QueryParam("defaultPicUrl") String defaultPicUrl,
            @QueryParam("shouldCheckSize") int shouldCheckSize,
            @QueryParam("voteIsStop") boolean voteIsStop,
            @QueryParam("MM") String MM
    ) {
        if (!MM.equals(VoteBasicInfo.ManagePassword)) {
            return false;
        }
        ManageVote.voteBasicInfo.VoteName = name;
        ManageVote.voteBasicInfo.VoteDes = des;
        ManageVote.voteBasicInfo.ShouldVoteCount = shouldCheckSize;
        ManageVote.voteBasicInfo.VoteIsStop = voteIsStop;
        ManageVote.voteBasicInfo.DefaultPicUrl = defaultPicUrl;
        return true;
    }

    @JSONP(queryParam = JSONP_CALLBACK)
    @GET
    @Path("passwordIsOk")
    public boolean passwordIsOk(@QueryParam("MM") String MM) {
        return MM.equals(VoteBasicInfo.ManagePassword);
    }

    @JSONP(queryParam = JSONP_CALLBACK)
    @GET
    @Path("clearDB")
    public boolean clearDB(@QueryParam("MM") String MM) {
        return ManageVote.clearDB(MM);
    }


}
