package tn.esprit.IRMC.presentation.mbeans;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.IRMC.persistence.Day;
import tn.esprit.IRMC.persistence.Library;
import tn.esprit.IRMC.persistence.LibraryAddress;
import tn.esprit.IRMC.persistence.LibraryType;
import tn.esprit.IRMC.persistence.TypeAcces;
import tn.esprit.IRMC.services.AddressServicesLocal;
import tn.esprit.IRMC.services.LibraryServicesLocal;



@ManagedBean
@SessionScoped
public class LibraryBean {

	@EJB
	LibraryServicesLocal ls;
	

	
	@EJB
	AddressServicesLocal as;
	
	private int length;
	
	private int LibraryId;
	private String Nom;
	private String LibrarySigle;
	private String LibraryWebSite;
	private String LibraryEmail;
	private Long LibraryPhoneNumer;
	
	private TypeAcces AccessType;
	private LibraryType type; 
	private Day Dayopening;
	private Day Dayclose;
	private Date TimeOpening;
	private Date TimeClose;
	private Double longitude;
	private Double altitude;
	
	private LibraryAddress address;
	
	private int id;
	private String country;
	private String city;
	private int code;
	private String Description;
	private String domaineSelected;
	
	private List<Library> libraries;
	private int Idlibtoupdate;
	private int IdAddtoupdate;
	private int IdSelected;
	private List<Library> searched;
	
	
	
	
	

	public List<Library> getSearched() {
		return searched;
	}
	public void setSearched(List<Library> searched) {
		this.searched = searched;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getIdSelected() {
		return IdSelected;
	}
	public void setIdSelected(int idSelected) {
		IdSelected = idSelected;
	}
	public int getIdAddtoupdate() {
		return IdAddtoupdate;
	}
	public void setIdAddtoupdate(int idAddtoupdate) {
		IdAddtoupdate = idAddtoupdate;
	}
	public int getIdlibtoupdate() {
		return Idlibtoupdate;
	}
	public void setIdlibtoupdate(int idlibtoupdate) {
		Idlibtoupdate = idlibtoupdate;
	}
	public List<Library> getLibraries() {
		return libraries;
	}
	public void setLibraries(List<Library> libraries) {
		this.libraries = libraries;
	}
	public int getLibraryId() {
		return LibraryId;
	}
	public void setLibraryId(int libraryId) {
		LibraryId = libraryId;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getLibrarySigle() {
		return LibrarySigle;
	}
	public void setLibrarySigle(String librarySigle) {
		LibrarySigle = librarySigle;
	}
	public String getLibraryWebSite() {
		return LibraryWebSite;
	}
	public void setLibraryWebSite(String libraryWebSite) {
		LibraryWebSite = libraryWebSite;
	}
	public String getLibraryEmail() {
		return LibraryEmail;
	}
	public void setLibraryEmail(String libraryEmail) {
		LibraryEmail = libraryEmail;
	}
	public Long getLibraryPhoneNumer() {
		return LibraryPhoneNumer;
	}
	public void setLibraryPhoneNumer(Long libraryPhoneNumer) {
		LibraryPhoneNumer = libraryPhoneNumer;
	}
	public TypeAcces getAccessType() {
		return AccessType;
	}
	public void setAccessType(TypeAcces accessType) {
		AccessType = accessType;
	}
	public LibraryType getType() {
		return type;
	}
	public void setType(LibraryType type) {
		this.type = type;
	}
	public Day getDayopening() {
		return Dayopening;
	}
	public void setDayopening(Day dayopening) {
		Dayopening = dayopening;
	}
	public Day getDayclose() {
		return Dayclose;
	}
	public void setDayclose(Day dayclose) {
		Dayclose = dayclose;
	}
	public Date getTimeOpening() {
		return TimeOpening;
	}
	public void setTimeOpening(Date timeOpening) {
		TimeOpening = timeOpening;
	}
	public Date getTimeClose() {
		return TimeClose;
	}
	public void setTimeClose(Date timeClose) {
		TimeClose = timeClose;
	}
	

	public String getDomaineSelected() {
		return domaineSelected;
	}
	public void setDomaineSelected(String domaineSelected) {
		this.domaineSelected = domaineSelected;
	}
	public LibraryAddress getAddress() {
		return address;
	}
	public void setAddress(LibraryAddress address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	
	
	
	
	
	
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getAltitude() {
		return altitude;
	}
	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}
	public String AddLibrary()
	{
		address =new LibraryAddress(getCountry(), getCity(), getCode(), getDescription());
		
		as.AddAddress(address);
		
		
	//	this.setLd(ds.findbycheck());
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		sdf.format(getTimeOpening());
		sdf.format(getTimeClose());
		Library l = new Library(getNom(), getLibrarySigle(), getLibraryWebSite(), getLibraryEmail(), getLibraryPhoneNumer(), getAccessType(), null, getDayopening(), getDayclose(), getTimeOpening(), getTimeClose(), null, null, getAddress());
		
		
		ls.AddLibrary(l);
		
		as.Assignlibrary(address, l);
	//	domaines= sb.getSelectedDomains();
	//	ls.AssignDominsToLibrary(domaines, l);
	//	ls.AssignAddress(address, l);
		
	//	ls.AssignDominsToLibrary(ld, l);
	
		return "/pages/admin/libraries?faces-redirect=true";
		
	}
	
	public List<Library> AllLibraries()
	{
		libraries= ls.FindALlLibraries();
		return libraries;
	}
	
	public void supprimer(int id)
	{
		ls.RemoveLibrary(id);
	}
	public String modifier (Library library)
	{
		
			this.setNom(library.getNom());
			this.setLibrarySigle(library.getLibrarySigle());
			this.setLibraryWebSite(library.getLibraryWebSite());
			this.setLibraryEmail(library.getLibraryEmail());
			this.setLibraryPhoneNumer(library.getLibraryPhoneNumer());
			this.setDayclose(library.getDayclose());
			this.setDayopening(library.getDayopening());
			this.setType(library.getType());
			this.setAccessType(library.getAccessType());
			this.setTimeOpening(library.getTimeOpening());
			this.setTimeClose(library.getTimeClose());
			
			this.setIdlibtoupdate(library.getLibraryId());
		
			this.setIdAddtoupdate(library.getAddress().getId());
			this.setCountry(library.getAddress().getCountry());
			this.setCity(library.getAddress().getCity());
			this.setDescription(library.getAddress().getDescription());
			this.setCode(library.getAddress().getCode());
			
			return "/pages/admin/UpdateLibrary?faces-redirect=true";
		
	}
	
	
	public String update()
	{
		ls.UpdateLibrary(new Library(getIdlibtoupdate(),getNom(), getLibrarySigle(), getLibraryWebSite(), getLibraryEmail(), getLibraryPhoneNumer(), getAccessType(), null, getDayopening(), getDayclose(), getTimeOpening(), getTimeClose(), null, null, getAddress()));

		as.UpdateAddress(new LibraryAddress(getIdAddtoupdate(),getCountry(),getCity(),getCode(),getDescription()));
		
		return "/pages/admin/libraries?faces-redirect=true";
	}
	
	
	
	public String details(Library library)
	
	{
		this.setNom(library.getNom());
		this.setLibrarySigle(library.getLibrarySigle());
		this.setLibraryWebSite(library.getLibraryWebSite());
		this.setLibraryEmail(library.getLibraryEmail());
		this.setLibraryPhoneNumer(library.getLibraryPhoneNumer());
		this.setDayclose(library.getDayclose());
		this.setDayopening(library.getDayopening());
		this.setType(library.getType());
		this.setAccessType(library.getAccessType());
		this.setTimeOpening(library.getTimeOpening());
		this.setTimeClose(library.getTimeClose());
		
		this.setCountry(library.getAddress().getCountry());
		this.setCity(library.getAddress().getCity());
		
		
		
		this.setIdSelected(library.getLibraryId());		
		
		return  "/pages/admin/LibraryDetails?faces-redirect=true";
		
	}
	
	
	public void assign(int id)
	
	{
		ls.AssignDominsToLibrary(id, getIdSelected() );
		System.out.println("errrrr");
	//	ls.AssignDominsToLibrary(domaines, library);
	}
	
	
	
	
	

	
	
	
	
	
	
	
	public String gotosearch()
	{
		return "/pages/Admin/ResultSearch?faces-redirect=true";
	}
	
	
	
	
	

	public String gotLibraries()
	{
		return "/pages/admin/libraries?faces-redirect=true";
	}
	
	
	
	
	
	
	
}
