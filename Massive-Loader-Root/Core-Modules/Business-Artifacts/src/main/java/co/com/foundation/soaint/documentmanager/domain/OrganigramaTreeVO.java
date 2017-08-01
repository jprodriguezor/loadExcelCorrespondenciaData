package co.com.foundation.soaint.documentmanager.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrodriguez on 25/10/2016.
 */
public class OrganigramaTreeVO {

    private TreeCoreVO core;
    private TreeCheckboxVO checkbox;
    private List<String> plugins;

    public OrganigramaTreeVO(TreeCoreVO core, TreeCheckboxVO checkbox,  List<String> plugins) {
        this.core = core;
        this.checkbox =checkbox;
        this.plugins =plugins;
    }

    public static OrganigramaTreeVO newInstance(List<OrganigramaItemVO> data){
        TreeCoreVO treeCoreVO =new TreeCoreVO(data);
        TreeCheckboxVO checkboxVO =new TreeCheckboxVO("false");

        List<String> plugins =new ArrayList<>();
        plugins.add("checkbox");
        plugins.add("search");

        return new OrganigramaTreeVO(treeCoreVO, checkboxVO, plugins);
    }

    public TreeCoreVO getCore() {
        return core;
    }

    public TreeCheckboxVO getCheckbox() {
        return checkbox;
    }

    public List<String> getPlugins() {
        return plugins;
    }
}

class TreeCoreVO{
    private List<OrganigramaItemVO> data;
    public TreeCoreVO(List<OrganigramaItemVO> data) {
        this.data = data;
    }
    public List<OrganigramaItemVO> getData() {
        return data;
    }
}

class TreeCheckboxVO{

    private String  keep_selected_style;
    public TreeCheckboxVO(String keep_selected_style) {
        this.keep_selected_style = keep_selected_style;
    }
    public String getKeep_selected_style() {
        return keep_selected_style;
    }
}

