# API REST : Offres d'emploi en Nouvelle-Calédonie


API Offres d'emploi de Nouvelle-Calédonie, cf sîte officiel : https://emploi.gouv.nc/

## Démarrer le service
 
`mvn spring-boot:run`

## Endpoints

```
/stats
/csv

/employeurs
/employeurs/{name}

/emploi/latest/
/emploi/latest/{quantity}
/emploi/{numero}
/emploi/{numero}/employeur
/emploi/previous/{nb}/{numeroOffre}
/emploi/next/{nb}/{numeroOffre}

/search/{nombreMaxOffres}/{MotsClès}/{commune}/{contrat}/{dateDebut}/{dateFin}
```

## Exemples d'appels

```
/stats
/csv

/employeurs
/employeurs/MRT

/emploi/latest
/emploi/latest/5
/emploi/4480
/emploi/4480/employeur
/emploi/previous/5/4719
/emploi/next/5/4719

/search/30/none/Nouméa/CDI/none/none
/search/30/none/Nouméa/CDI/01042020/10042020
```

## Marketplace et documentation
 
 Tout est disponible sur [Marketplace RapidAPI](https://rapidapi.com/adriens/api/emploi-nouvelle-caledonie).
 
## Ambitions
 
L'ambition de cette API est de promouvoir l'Open Data, les intégrations diverses, le développement
de solutions innovantes et efficaces sur le modèle de l'Open Innovation, notamment avec les passionnés d'IT
et les partenaires pédagogiques.


### Exemples de réalisations
 
- [ ] Application mobile Flutter : en cours de réalisation cf [#5](https://github.com/adriens/emploi-nc-api/issues/5) cf le [projet dédié](https://github.com/adriens/emploi-nc-app)
