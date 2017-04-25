package com.st.demo.web.security;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

public class MyUserDetailsService extends JdbcDaoSupport implements UserDetailsService {

	protected final Log logger = LogFactory.getLog(getClass());
	
	public static final String DEF_USERS_BY_USERNAME_QUERY =
            "select username,password,enabled " +
            "from users " +
            "where username = ?";
    public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY =
            "select username,authority " +
            "from authorities " +
            "where username = ?";
    public static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY =
            "select g.id, g.group_name, ga.authority " +
            "from groups g, group_members gm, group_authorities ga " +
            "where gm.username = ? " +
            "and g.id = ga.group_id " +
            "and g.id = gm.group_id";

	protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	private String authoritiesByUsernameQuery;
	private String groupAuthoritiesByUsernameQuery;
	private String usersByUsernameQuery;
	private String rolePrefix = "";
	private boolean usernameBasedPrimaryKey = true;
	private boolean enableAuthorities = true;
	private boolean enableGroups;
	
	public MyUserDetailsService(){
		usersByUsernameQuery = DEF_USERS_BY_USERNAME_QUERY;
        authoritiesByUsernameQuery = DEF_AUTHORITIES_BY_USERNAME_QUERY;
        groupAuthoritiesByUsernameQuery = DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY;
	}

	protected void addCustomAuthorities(String username, List<GrantedAuthority> authorities) {
	}

	public String getUsersByUsernameQuery() {
		return usersByUsernameQuery;
	}

	protected void initDao() throws ApplicationContextException {
		Assert.isTrue(enableAuthorities || enableGroups, "Use of either authorities or groups must be enabled");
	}

	public MyUser loadUserByUsername(String username) throws UsernameNotFoundException {
		List<MyUser> users = loadUsersByUsername(username);

		if (users.size() == 0) {
			logger.debug("Query returned no results for user '" + username + "'");

			throw new UsernameNotFoundException(
					messages.getMessage("JdbcDaoImpl.notFound", new Object[] { username }, "Username {0} not found"),
					username);
		}

		MyUser user= users.get(0); // contains no GrantedAuthority[]

		Set<GrantedAuthority> dbAuthsSet = new HashSet<GrantedAuthority>();

		if (enableAuthorities) {
			dbAuthsSet.addAll(loadUserAuthorities(user.getUsername()));
		}

		if (enableGroups) {
			dbAuthsSet.addAll(loadGroupAuthorities(user.getUsername()));
		}

		List<GrantedAuthority> dbAuths = new ArrayList<GrantedAuthority>(dbAuthsSet);

		addCustomAuthorities(user.getUsername(), dbAuths);

		if (dbAuths.size() == 0) {
			logger.debug("User '" + username + "' has no authorities and will be treated as 'not found'");

			throw new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.noAuthority",
					new Object[] { username }, "User {0} has no GrantedAuthority"), username);
		}

		return createUserDetails(username, user, dbAuths,user.getEmail());
	}

	/**
	 * Executes the SQL <tt>usersByUsernameQuery</tt> and returns a list of
	 * UserDetails objects. There should normally only be one matching user.
	 */
	protected List<MyUser> loadUsersByUsername(String username) {
		return getJdbcTemplate().query(usersByUsernameQuery, new String[] { username }, new RowMapper<MyUser>() {
			public MyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
				String username = rs.getString(1);
				String password = rs.getString(2);
				boolean enabled = rs.getBoolean(3);
				String email=rs.getString(4);
				return new MyUser(username, password, enabled, true, true, true, AuthorityUtils.NO_AUTHORITIES,email);
			}

		});
	}

	/**
	 * Loads authorities by executing the SQL from
	 * <tt>authoritiesByUsernameQuery</tt>.
	 *
	 * @return a list of GrantedAuthority objects for the user
	 */
	protected List<GrantedAuthority> loadUserAuthorities(String username) {
		return getJdbcTemplate().query(authoritiesByUsernameQuery, new String[] { username },
				new RowMapper<GrantedAuthority>() {
					public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
						String roleName = rolePrefix + rs.getString(2);

						return new SimpleGrantedAuthority(roleName);
					}
				});
	}

	/**
	 * Loads authorities by executing the SQL from
	 * <tt>groupAuthoritiesByUsernameQuery</tt>.
	 *
	 * @return a list of GrantedAuthority objects for the user
	 */
	protected List<GrantedAuthority> loadGroupAuthorities(String username) {
		return getJdbcTemplate().query(groupAuthoritiesByUsernameQuery, new String[] { username },
				new RowMapper<GrantedAuthority>() {
					public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
						String roleName = getRolePrefix() + rs.getString(3);

						return new SimpleGrantedAuthority(roleName);
					}
				});
	}

	/**
	 * Can be overridden to customize the creation of the final
	 * UserDetailsObject which is returned by the <tt>loadUserByUsername</tt>
	 * method.
	 *
	 * @param username
	 *            the name originally passed to loadUserByUsername
	 * @param userFromUserQuery
	 *            the object returned from the execution of the
	 * @param combinedAuthorities
	 *            the combined array of authorities from all the authority
	 *            loading queries.
	 * @return the final UserDetails which should be used in the system.
	 */
	protected MyUser createUserDetails(String username, MyUser userFromUserQuery,
			List<GrantedAuthority> combinedAuthorities,String email) {
		String returnUsername = userFromUserQuery.getUsername();

		if (!usernameBasedPrimaryKey) {
			returnUsername = username;
		}

		return new MyUser(returnUsername, userFromUserQuery.getPassword(), userFromUserQuery.isEnabled(), true, true,
				true, combinedAuthorities,email);
	}

	public void setAuthoritiesByUsernameQuery(String queryString) {
		authoritiesByUsernameQuery = queryString;
	}

	protected String getAuthoritiesByUsernameQuery() {
		return authoritiesByUsernameQuery;
	}

	public void setGroupAuthoritiesByUsernameQuery(String queryString) {
		groupAuthoritiesByUsernameQuery = queryString;
	}

	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}

	protected String getRolePrefix() {
		return rolePrefix;
	}

	public void setUsernameBasedPrimaryKey(boolean usernameBasedPrimaryKey) {
		this.usernameBasedPrimaryKey = usernameBasedPrimaryKey;
	}

	protected boolean isUsernameBasedPrimaryKey() {
		return usernameBasedPrimaryKey;
	}

	public void setUsersByUsernameQuery(String usersByUsernameQueryString) {
		this.usersByUsernameQuery = usersByUsernameQueryString;
	}

	protected boolean getEnableAuthorities() {
		return enableAuthorities;
	}

	/**
	 * Enables loading of authorities (roles) from the authorities table.
	 * Defaults to true
	 */
	public void setEnableAuthorities(boolean enableAuthorities) {
		this.enableAuthorities = enableAuthorities;
	}

	protected boolean getEnableGroups() {
		return enableGroups;
	}

	/**
	 * Enables support for group authorities. Defaults to false
	 * 
	 * @param enableGroups
	 */
	public void setEnableGroups(boolean enableGroups) {
		this.enableGroups = enableGroups;
	}

}
