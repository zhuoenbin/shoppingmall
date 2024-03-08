package com.ispan.projectX.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_permissions")
public class EmployeePermissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long permissionId;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "db_authority", unique = true, nullable = false)
    private String dbAuthority;

    @Column(name = "db_authority_description")
    private String dbAuthorityDescription;

    @Column(name = "webpage_tables")
    private String webpageTables;

    @Column(name = "webpage_details")
    private String webpageDetails;

    public EmployeePermissions() {
    }

    public EmployeePermissions(Long permissionId, String department, String title, String dbAuthority, String dbAuthorityDescription, String webpageTables, String webpageDetails) {
        this.permissionId = permissionId;
        this.department = department;
        this.title = title;
        this.dbAuthority = dbAuthority;
        this.dbAuthorityDescription = dbAuthorityDescription;
        this.webpageTables = webpageTables;
        this.webpageDetails = webpageDetails;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDbAuthority() {
        return dbAuthority;
    }

    public void setDbAuthority(String dbAuthority) {
        this.dbAuthority = dbAuthority;
    }

    public String getDbAuthorityDescription() {
        return dbAuthorityDescription;
    }

    public void setDbAuthorityDescription(String dbAuthorityDescription) {
        this.dbAuthorityDescription = dbAuthorityDescription;
    }

    public String getWebpageTables() {
        return webpageTables;
    }

    public void setWebpageTables(String webpageTables) {
        this.webpageTables = webpageTables;
    }

    public String getWebpageDetails() {
        return webpageDetails;
    }

    public void setWebpageDetails(String webpageDetails) {
        this.webpageDetails = webpageDetails;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EmployeePermissions{");
        sb.append("permissionId=").append(permissionId);
        sb.append(", department='").append(department).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", dbAuthority='").append(dbAuthority).append('\'');
        sb.append(", dbAuthorityDescription='").append(dbAuthorityDescription).append('\'');
        sb.append(", webpageTables='").append(webpageTables).append('\'');
        sb.append(", webpageDetails='").append(webpageDetails).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
