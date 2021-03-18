# Play-HttpQuery-Datatables

[![Latest release](https://img.shields.io/github/v/release/PierreAdam/play-httpquery-datatables)](https://github.com/PierreAdam/play-httpquery-datatables/releases/latest)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/2f31b4b64aba49e2aa2bc2be18516fe2)](https://www.codacy.com/gh/PierreAdam/play-httpquery-datatables/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=PierreAdam/play-httpquery-datatables&amp;utm_campaign=Badge_Grade)
[![Code Climate maintainability](https://img.shields.io/codeclimate/maintainability-percentage/PierreAdam/play-httpquery-datatables)](https://codeclimate.com/github/PierreAdam/play-httpquery-datatables)
[![Snyk Vulnerabilities](https://img.shields.io/snyk/vulnerabilities/github/PierreAdam/play-httpquery-datatables)](https://snyk.io/test/github/PierreAdam/play-httpquery-datatables?targetFile=pom.xml)
![JDK](https://img.shields.io/badge/JDK-1.8+-blue.svg)
[![Build Status](https://travis-ci.com/PierreAdam/play-httpquery-datatables.svg?branch=master)](https://travis-ci.com/PierreAdam/play-httpquery-datatables)
[![GitHub license](https://img.shields.io/github/license/PierreAdam/play-httpquery-datatables)](https://raw.githubusercontent.com/PierreAdam/play-httpquery-datatables/master/LICENSE)

Play-HttpQuery-Datatables is a library for play framework that allows you to easily integrate [Datatables](https://datatables.net/) in your Play project to use an API build
around [Play-Ebean-HttpQuery](https://github.com/thibaultmeyer/play-ebean-httpquery).

*****

## Build the library

```shell
$> mvn compile
$> mvn package
```

### Install or deploy

To install in your local repository.

```shell
$> mvn install
```

To deploy to a remote repository.

```shell
$> mvn verify
$> mvn deploy
```

## How to import the library

In your ```build.sbt``` file, you need to add a resolver to jitpack and the dependency for the module. This translate to the following lines.

```scala
libraryDependencies += "com.jackson42.play" % "play-httpquery-datatables" % "21.03u1"
```

## How to use the library

This library is made to utilise an API build around [Play-Ebean-HttpQuery](https://github.com/thibaultmeyer/play-ebean-httpquery) or API being able to handle similar type of
queries.

### Important points

#### Your controller

For easier use, your controller will need to implement the interface `PlayEbeanDataTables`. This will allows you to directly return the method `datatablesAjaxRequest` in your
handling of the datatable query. The request will be automatically parsed and analyzed. All you will need to do is to forge the answer by using the object `EbeanDataTableQuery`.

A minimal controller would look like the following.

```java
import com.jackson42.play.datatables.entities.internal.DataSource;
import com.jackson42.play.httpquerydatatables.HttpQueryDataTables;
import com.jackson42.play.httpquerydatatables.HttpQueryProvider;
import play.i18n.MessagesApi;

import java.util.Map;
import java.util.function.Function;

public class MyController extends Controller implements DataTablesHelper {
    private final FormFactory formFactory;

    private final HttpQueryDataTables<MyEntity, HttpQueryProvider<MyEntity>> edt;

    // A good practice is to make your own provider that fit your needs.
    public static class MyProvider extends HttpQueryProvider<MyEntity> {
        
        public MyProvider() {
            super(provider -> {
                // Here is your custom querying logic.
                final Map<String, String> queryArgs = provider.forgeArguments();

                // To utilize the pagination
                provider.getPage();
                provider.getPerPage();
                provider.getFirstItemIdx();
                provider.getLastItemIdx();

                // Do the querying
                FakeHttpResult result = FakeHttpRequest.get("http://my-api.com", provider.getPage(), provider.getPerPage(), queryArgs);

                // Forge the DataSource as result
                final DataSource<MyEntity> dataSource = new DataSource<>();

                // Set the total number of record WITHOUT the filtering (must be computed on your API side)
                dataSource.setRecordsTotal(result.getTotalRecords);
                // Set the total number of record WITH the filtering (must be computed on your API side)
                dataSource.setRecordsFiltered(result.getFilteredRecords);
                // Set the list of entities for this call
                dataSource.setEntities(result.getEntities());

                return dataSource;
            });
        }
    }

    @Inject
    public MyController(final FormFactory formFactory, final MessagesApi messagesApi) {
        this.formFactory = formFactory;
        this.edt = HttpQueryDataTables.create(MyEntity.class, messagesApi, () -> {
            // Create a new provider for each request. 
            return new MyProvider();
        });

        // Set a custom display suppliers.
        this.edt.setFieldDisplaySupplier("myField", (myEntity, context) -> myEntity.getMyApiField().trim());
        // Set a custom search handler to be able to match a partial search in the database.
        this.edt.setSearchHandler("myField", (query, search) -> query.icontains("myApiField", search));
        // Set a custom order handler.
        this.edt.setOrderHandler("myField", (query, orderEnum) -> query.orderBy("myApiField", orderEnum));
    }

    public GET_MyPage(final Http.Request request) {
        return Results.ok(MyPage.render());
    }

    public POST_AjaxDatatable(final Http.Request request) {
        return this.dataTablesAjaxRequest(request, this.formFactory,
                boundForm -> {
                    // Error Callback
                    return Results.badRequest();
                },
                form -> {
                    // Success Callback
                    return Results.ok(edt.getAjaxResult(parameters));
                });
    }
}
```

#### The models

EbeanDataTableQuery will automatically analyze the parameters send from your page, and a query will be build accordingly to your model and data in your view. It is important that
your models have getters in order for the values to be retrieved from them.

If you have a field called `foo`, the following getter name will be tried :

- `getFoo()`
- `isFoo()`
- `hasFoo()`
- `canFoo()`
- `foo()`

### Your webpage

Your webpage can be build using the scala template engine or anything else. The exemple bellow assume that you are using the scala templates

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="//cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
</head>
<body>
<table id="my-list">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>
    </thead>
</table>

<script>
    $(document).ready(function () {
        table = $('#my-list').DataTable({
            processing: true,
            serverSide: true,
            ajax: {
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "@controllers.routes.MyController.POST_AjaxDatatable",
                data: function (d) {
                    return JSON.stringify({parameters: d});
                }
            },
            searching: true,
            dom: "ltipr",
            columns: [{
                name: "id",
                orderable: true,
                searchable: false
            }, {
                name: "name",
                orderable: true,
                searchable: true
            }, {
                name: "email",
                orderable: true,
                searchable: true
            }, {
                name: "actions",
                orderable: false,
                searchable: false
            }],
            order: [[0, "asc"]],
            columnDefs: []
        });
    });
</script>
</body>
</html>
```

## Versions

| Library Version | Play Version | HttpQuery Version | Tested DataTables Version  |
|-----------------|--------------|-------------------|----------------------------|
| 21.03u1         | 2.8.x        | 20.08             | 1.10.x                     |
| 21.03           | 2.8.x        | 20.08             | 1.10.x                     |

### Changelog

#### 21.03u1

- The provider is now a templated parameter allowing more freedom when creating your own provider.

#### 21.03

- First version.
- Allows a custom behavior on the Provider.

## License

This project is released under terms of the [MIT license](https://raw.githubusercontent.com/PierreAdam/play-ebean-datatables/master/LICENSE).
