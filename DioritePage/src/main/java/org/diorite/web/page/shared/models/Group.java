package org.diorite.web.page.shared.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "groups")
public class Group implements Serializable
{
    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    // PERMISSIONS  //

    private boolean canAccessForum;
    private boolean canUseAdminCp;

    private boolean canCreateThread;
    private boolean canCreatePost;

    // /PERMISSIONS //

    public Group()
    {
    }

    public Group(final String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return this.id;
    }

    public void setId(final int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public boolean isCanAccessForum()
    {
        return this.canAccessForum;
    }

    public void setCanAccessForum(final boolean canAccessForum)
    {
        this.canAccessForum = canAccessForum;
    }

    public boolean isCanUseAdminCp()
    {
        return this.canUseAdminCp;
    }

    public void setCanUseAdminCp(final boolean canUseAdminCp)
    {
        this.canUseAdminCp = canUseAdminCp;
    }

    public boolean isCanCreatePost()
    {
        return this.canCreatePost;
    }

    public void setCanCreatePost(final boolean canCreatePost)
    {
        this.canCreatePost = canCreatePost;
    }

    public boolean isCanCreateThread()
    {
        return this.canCreateThread;
    }

    public void setCanCreateThread(final boolean canCreateThread)
    {
        this.canCreateThread = canCreateThread;
    }
}
