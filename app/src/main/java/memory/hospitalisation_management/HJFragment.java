package memory.hospitalisation_management;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class HJFragment extends Fragment {
    private FragmentActivity fragmentActivity;
    FragmentManager fragmentManager=null;


    public HJFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_hj, container, false);
        ViewPager viewPager=view.findViewById(R.id.pagesVp);
        TabLayout tabLayout=view.findViewById(R.id.slidingTL);
        fragmentManager= getChildFragmentManager();
        MyPagerAdapter1 adapter=new MyPagerAdapter1(fragmentActivity,fragmentManager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;


    }
    @Override
    public void onAttach(Context context) {
        fragmentActivity= (FragmentActivity) context;
        super.onAttach(context);
    }
}

class MyPagerAdapter1 extends FragmentPagerAdapter {
    Context context;
    public MyPagerAdapter1(Context context,FragmentManager fm) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f=null;
        switch (position){
            case 0:
                f=new HommeHjFragment();
                break;
            case 1:
                f= new FemmeHjFragment();
                break;
        }
        return f;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Homme";
            case 1:
                return "Femme";
        }
        return null;
    }


}
