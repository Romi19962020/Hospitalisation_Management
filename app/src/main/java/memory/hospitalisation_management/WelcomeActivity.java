package memory.hospitalisation_management;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toolbar;

public class WelcomeActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navi;
    private ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        drawerLayout= findViewById(R.id.draw);
        navi=findViewById(R.id.nav);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.Open,R.string.Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(navi);

       /* PatientsFragment patientsFragment=new PatientsFragment();
        FragmentManager fm= getSupportFragmentManager();
        fm.beginTransaction().add(R.id.contentFl,patientsFragment).commit();*/

    }

    private void setActionBarTitle(String Tableau_de_bord) {
        getSupportActionBar().setTitle(Tableau_de_bord);

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    public void selectIterDrawer(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.tab:
                fragmentClass = TabFragment.class;
                break;
            case R.id.agenda:
                fragmentClass = AgendaFragment.class;
                break;
            case R.id.patients:
                fragmentClass = PatientsFragment.class;
                break;
                case R.id.cons:
                fragmentClass = ConsultationFragment.class;
                break;
            case R.id.hosp:
                fragmentClass =HospitalisationFragment.class;
                break;
            case R.id.hj:
                fragmentClass = HJFragment.class;
                break;
                default:
                fragmentClass=TabFragment.class;
        }
        try {
            fragment= (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contentFl,fragment).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }
    private void setupDrawerContent( NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectIterDrawer(menuItem);
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    }

