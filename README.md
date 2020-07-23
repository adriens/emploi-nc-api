# API REST : Offres d'emploi en Nouvelle-Calédonie


API Offres d'emploi de Nouvelle-Calédonie, cf sîte officiel : https://emploi.gouv.nc/

## Démarrer le service
 
`mvn spring-boot:run`

## Endpoints

Le endpoint racine vous renvoie vers une documentation généré par open api.

```
/stats
/csv
/excel

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
/excel

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

-----------------------

# Intégration des données dans ELK

## Configurer Logstash

#### Exemple de configuration

```
# /etc/logstash/conf.d/logstash_01.conf

input {
  file {
    path => ["/$PATH_TO_FILES/stat-*.csv"]
    start_position => "beginning"
    sincedb_path => "/var/lib/logstash/sample_sincedb"
  }
}

filter {
    csv {
        separator => ","
        skip_header => true
        columns => [ 
        	"url", "numeroOffre", "titreOffre", "nomEntreprise", 
        	"aPourvoirLe", "communeEmploi", "experience", "niveauFormation", 
        	"diplome", "nbPostes", "datePublication", "typecontrat", 
        	"province", "latitude", "longitude", "urlgooglemap" 
    	]
    }
    mutate { remove_field => [ "urlgooglemap" ] }
    mutate { convert => {"latitude" => "float"} }
    mutate { convert => {"longitude" => "float"} }
    mutate { rename => {"latitude" => "[location][lat]"} }
    mutate { rename => {"longitude" => "[location][lon]"} }
}

output {
  elasticsearch {
    hosts => "http://localhost:9200"
    index => "stat"
    document_type => "_doc"
  }
  stdout {}
}
```

## Lancement des services ELK

#### Lancement Elasticsearch
```
sudo service elasticsearch start
```

#### Lancement Logstash et création index Elasticsearch
```
$PATH_TO_LOGSTASH/bin/logstash -f /etc/logstash.conf.d/logstash_01.conf
```

#### Lancement Kibana
```
sudo service kibana start
```

## Accès aux données dans Kibana

### Configuration du template des données
#### Exemple de configuration "geo_point"
`Via la console de l'onglet "Dev Tools" ou curl`

```
PUT _template/stat?include_type_name=true
{
  "index_patterns": [
      "stat*"
    ],
  "settings": {},
  "mappings": {
    "_doc": {
      "properties": {
        "location": {
          "type": "geo_point"
        }
      }
    }
  }
}
```
![devtools](https://user-images.githubusercontent.com/41884305/88335952-e3866d80-cd7f-11ea-92f4-ddddad990d1e.png)

### Vérification de la création de l'index Elasticsearch
`"Management" > "Elasticsearch" "Index Management"`
![elasticsearch_index](https://user-images.githubusercontent.com/41884305/88335919-d36e8e00-cd7f-11ea-8abc-dd2bc92da431.png)

### Création de l'index Kibana
`"Management" > "Kibana" "Index Pattern" > "Create index pattern"`
![kibana_index](https://user-images.githubusercontent.com/41884305/88335916-cf427080-cd7f-11ea-9f4c-3224263bc4bd.png)

### Vérification des données dans l'onglet "Discover"
![discover_data](https://user-images.githubusercontent.com/41884305/88335910-cc478000-cd7f-11ea-8fb2-401728533167.png)

## Création de visualisations
![visualization_data](https://user-images.githubusercontent.com/41884305/88335885-c2258180-cd7f-11ea-8173-42a0cda46a6e.png)
![pie_data_typecontrat](https://user-images.githubusercontent.com/41884305/88335880-bfc32780-cd7f-11ea-9b74-f6f20237bcf4.png)
![map_data](https://user-images.githubusercontent.com/41884305/88335872-bd60cd80-cd7f-11ea-8d26-dfbc3153cf5f.png)


## Création d'un Dashboard
![dashboard_data](https://user-images.githubusercontent.com/41884305/88335857-b5a12900-cd7f-11ea-9bdf-c279233bb897.png)
