package com.eftomi.tcp.dto;

public class DisplaySectionDTO {
	private boolean menuNav;
	private boolean aboutNav;
	private boolean packagingPolicyNav;
	private boolean packagingInstructionNav;
	private boolean emptiesListNav;
	private boolean createCargoListSelectNav;
	private boolean createCargoListMainNav;
	private boolean modifyQuantityNav;

	public DisplaySectionDTO(boolean menuNav, boolean aboutNav, boolean packagingPolicyNav,
			boolean packagingInstructionNav, boolean emptiesListNav, boolean createCargoListSelectNav,
			boolean createCargoListMainNav, boolean modifyQuantityNav) {
		super();
		this.menuNav = menuNav;
		this.aboutNav = aboutNav;
		this.packagingPolicyNav = packagingPolicyNav;
		this.packagingInstructionNav = packagingInstructionNav;
		this.emptiesListNav = emptiesListNav;
		this.createCargoListSelectNav = createCargoListSelectNav;
		this.createCargoListMainNav = createCargoListMainNav;
		this.modifyQuantityNav = modifyQuantityNav;
	}

	public DisplaySectionDTO() {
		super();
	}

	public boolean isMenuNav() {
		return menuNav;
	}
	public void setMenuNav(boolean menuNav) {
		this.menuNav = menuNav;
	}
	public boolean isAboutNav() {
		return aboutNav;
	}
	public void setAboutNav(boolean aboutNav) {
		this.aboutNav = aboutNav;
	}
	public boolean isPackagingPolicyNav() {
		return packagingPolicyNav;
	}
	public void setPackagingPolicyNav(boolean packagingPolicyNav) {
		this.packagingPolicyNav = packagingPolicyNav;
	}
	public boolean isPackagingInstructionNav() {
		return packagingInstructionNav;
	}
	public void setPackagingInstructionNav(boolean packagingInstructionNav) {
		this.packagingInstructionNav = packagingInstructionNav;
	}
	public boolean isEmptiesListNav() {
		return emptiesListNav;
	}
	public void setEmptiesListNav(boolean emptiesListNav) {
		this.emptiesListNav = emptiesListNav;
	}
	public boolean isCreateCargoListSelectNav() {
		return createCargoListSelectNav;
	}
	public void setCreateCargoListSelectNav(boolean createCargoListSelectNav) {
		this.createCargoListSelectNav = createCargoListSelectNav;
	}
	public boolean isCreateCargoListMainNav() {
		return createCargoListMainNav;
	}
	public void setCreateCargoListMainNav(boolean createCargoListMainNav) {
		this.createCargoListMainNav = createCargoListMainNav;
	}
	public boolean isModifyQuantityNav() {
		return modifyQuantityNav;
	}
	public void setModifyQuantityNav(boolean modifyQuantityNav) {
		this.modifyQuantityNav = modifyQuantityNav;
	}
	
	
}
