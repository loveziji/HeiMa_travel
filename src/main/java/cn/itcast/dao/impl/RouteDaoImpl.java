package cn.itcast.dao.impl;

import cn.itcast.bean.Route;
import cn.itcast.dao.RouteDao;
import cn.itcast.dao.UserDao;
import cn.itcast.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findTotalCount(int cid,String rname) {
//        String sql = "select count(*) from tab_route where cid = ?";
        //1.定义sql模板
        String sql = "select count(*) from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //条件们
        List params = new ArrayList();
        //2.判断参数是否有值
        //返回的就一个值
        if (cid != 0 ){
            sb.append(" and cid = ? ");
            //添加？对应的值
            params.add(cid);
        }
        if (rname != null && rname.length() > 0){
            sb.append(" add rname like ?");
            params.add("%"+rname+"%");
        }
        sql = sb.toString();
//        return template.queryForObject(sql,Integer.class,cid);
        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String rname) {
//        String sql = "select * from tab_route where cid = ? and rname like ? limit ?,?";
        //1.定义sql模板
        String sql = "select * from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //条件们
        List params = new ArrayList();
        //2.判断参数是否有值
        //返回的就一个值
        if (cid != 0 ){
            sb.append(" and cid = ? ");
            //添加？对应的值
            params.add(cid);
        }
        if (rname != null && rname.length() > 0){
            sb.append(" add rname like ?");
            params.add("%"+rname+"%");
        }
        //分页条件
        sb.append(" limit ? , ?");
        sql = sb.toString();

        params.add(start);
        params.add(pageSize);
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }
}
