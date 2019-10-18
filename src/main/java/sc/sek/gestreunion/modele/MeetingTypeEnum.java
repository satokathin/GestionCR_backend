package sc.sek.gestreunion.modele;

public enum MeetingTypeEnum {
    CD ("Culte du dimanche de l'assemblée"),
    CPA ("Croisade de prière de l'assemblée"),
    CPE ("Croisade de prière de l'église"),
    PBA ("Reunion de prière bimensuel de l'assemblée"),
    IJMF ("Intercession Jeunante de l'église pour la France le Mercredi"),
    PCP ("Prière de la chambre de prière de l'église"),
    SEA ("Siège d'enseignement de l'assemblée"),
    RAM ("Reunion d'assemblée de maison");

    private String name = "";

    MeetingTypeEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MeetingTypeEnum{" +
                "name='" + name + '\'' +
                '}';
    }
}
