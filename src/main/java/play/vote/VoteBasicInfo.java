/**
 * Author: WuHaoLin
 * Date: 2014/5/8
 * Time: 21:30
 */

package play.vote;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/8
 * Time: 21:30
 */
public class VoteBasicInfo {

	/**
	 * 当图片url为空时的默认图片URL
	 */
	public String DefaultPicUrl="";

	/**
	 * 投票活动名称
	 */
	public String VoteName = "投票";
	/**
	 * 投票活动简介
	 */
	public String VoteDes = "";

	/**
	 * 管理员后台
	 */
	public static final String ManagePassword = "VOTE";

	/**
	 * 开启或停止投票
	 */
	public boolean VoteIsStop = true;//投票活动是否截至

	/**
	 * 投票者每次都需要选择多少人
	 */
	public int ShouldVoteCount = 5;

}
